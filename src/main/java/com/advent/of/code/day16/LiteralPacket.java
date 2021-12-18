package com.advent.of.code.day16;

class LiteralPacket implements Packet{
    private final int version;
    private final int typeId;
    private final long literal;

    LiteralPacket(int version, int typeId, long literal) {
        this.version = version;
        this.typeId = typeId;
        this.literal = literal;
    }

    @Override
    public int getVersion() {
        return this.version;
    }

    @Override
    public int getTypeId() {
        return typeId;
    }

    long getLiteral() {
        return literal;
    }

    @Override
    public PacketType getType() {
        return PacketType.LITERAL;
    }
}
