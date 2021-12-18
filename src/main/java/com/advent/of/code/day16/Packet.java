package com.advent.of.code.day16;

import java.util.ArrayList;
import java.util.List;

interface Packet {
    int getVersion();

    PacketType getType();

    int getTypeId();

    default List<Packet> getSubpackets() {
        return new ArrayList<>();
    }
}
