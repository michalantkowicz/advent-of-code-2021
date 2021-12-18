package com.advent.of.code.day16;

import java.util.ArrayList;
import java.util.List;

class OperatorPacket implements Packet{
    private final int version;
    private final int typeId;
    private final List<Packet> subpackets = new ArrayList<>();

    OperatorPacket(int version, int typeId) {
        this.version = version;
        this.typeId = typeId;
    }

    void addSubpacket(Packet packet) {
        this.subpackets.add(packet);
    }

    @Override
    public List<Packet> getSubpackets() {
        return this.subpackets;
    }

    @Override
    public int getVersion() {
        return this.version;
    }

    @Override
    public int getTypeId() {
        return typeId;
    }

    @Override
    public PacketType getType() {
        return PacketType.OPERATOR;
    }
}
