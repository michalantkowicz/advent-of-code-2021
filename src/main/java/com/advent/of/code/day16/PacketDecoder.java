package com.advent.of.code.day16;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.stream.Collectors;

class PacketDecoder {
    int getSumOfVersions(String input) {
        final AtomicInteger versionsSum = new AtomicInteger();
        final String binaryInput = new HexDecoder().decode(input);
        traversePackets(parse(binaryInput).first(), p -> versionsSum.addAndGet(p.getVersion()));
        return versionsSum.get();
    }

    long getResult(String input) {
        final String binaryInput = new HexDecoder().decode(input);
        final Packet packet = parse(binaryInput).first();
        return processPacket(packet);
    }

    private long processPacket(Packet packet) {
        if (packet.getType().equals(PacketType.LITERAL)) {
            return ((LiteralPacket) packet).getLiteral();
        } else {
            final List<Long> subpacketsValues = packet.getSubpackets().stream()
                    .map(this::processPacket)
                    .collect(Collectors.toList());

            return switch (packet.getTypeId()) {
                case 0 -> subpacketsValues.stream().mapToLong(Long::longValue).sum();
                case 1 -> (subpacketsValues.size() == 1) ? subpacketsValues.get(0) : subpacketsValues.stream().mapToLong(Long::longValue).reduce(1L, (a, b) -> a * b);
                case 2 -> subpacketsValues.stream().mapToLong(Long::longValue).min().getAsLong();
                case 3 -> subpacketsValues.stream().mapToLong(Long::longValue).max().getAsLong();
                case 5 -> (subpacketsValues.get(0) > subpacketsValues.get(1)) ? 1L : 0L;
                case 6 -> (subpacketsValues.get(0) < subpacketsValues.get(1)) ? 1L : 0L;
                case 7 -> (Objects.equals(subpacketsValues.get(0), subpacketsValues.get(1))) ? 1L : 0L;
                default -> throw new UnsupportedOperationException();
            };
        }
    }

    private Tuple<? extends Packet, String> parse(String binaryInput) {
        final int type = (int)fromBinary(binaryInput.substring(3, 6));
        if (type == 4) {
            return parseLiteral(binaryInput);
        } else {
            return parseOperator(binaryInput);
        }
    }

    private Tuple<LiteralPacket, String> parseLiteral(String binaryInput) {
        final int version = (int)fromBinary(binaryInput.substring(0, 3));
        final int typeId = (int)fromBinary(binaryInput.substring(3, 6));
        final StringBuilder literal = new StringBuilder();
        int i = 6;
        for (; i + 5 <= binaryInput.length(); i += 5) {
            final String part = binaryInput.substring(i, i + 5);
            literal.append(part.substring(1));
            if (part.charAt(0) == '0') {
                break;
            }
        }
        final LiteralPacket literalPacket = new LiteralPacket(version, typeId, fromBinary(literal.toString()));
        return new Tuple<>(literalPacket, binaryInput.substring(i + 5));
    }

    private Tuple<OperatorPacket, String> parseOperator(String binaryInput) {
        final int version = (int)fromBinary(binaryInput.substring(0, 3));
        final int typeId = (int)fromBinary(binaryInput.substring(3, 6));
        final OperatorPacket result = new OperatorPacket(version, typeId);

        final int lengthType = (int)fromBinary(binaryInput.substring(6, 7));

        if (lengthType == 0) {
            final int subpacketsTotalLength = (int)fromBinary(binaryInput.substring(7, 22));
            String subpacketsBits = binaryInput.substring(22, 22 + subpacketsTotalLength);
            while (!subpacketsBits.isEmpty()) {
                final Tuple<? extends Packet, String> parseResult = parse(subpacketsBits);
                result.addSubpacket(parseResult.first());
                subpacketsBits = parseResult.second();
            }
            return new Tuple<>(result, binaryInput.substring(22 + subpacketsTotalLength));
        } else {
            final int numberOfSubpackets = (int)fromBinary(binaryInput.substring(7, 18));
            String subpacketsBits = binaryInput.substring(18);
            for (int i = 0; i < numberOfSubpackets; i++) {
                final Tuple<? extends Packet, String> parseResult = parse(subpacketsBits);
                result.addSubpacket(parseResult.first());
                subpacketsBits = parseResult.second();
            }
            return new Tuple<>(result, subpacketsBits);
        }
    }

    private long fromBinary(String bits) {
        return Long.parseLong(bits, 2);
    }

    private void traversePackets(Packet packet, Consumer<Packet> operation) {
        operation.accept(packet);
        for (Packet subpacket : packet.getSubpackets()) {
            traversePackets(subpacket, operation);
        }
    }
}
