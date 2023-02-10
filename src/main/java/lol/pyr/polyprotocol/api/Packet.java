package lol.pyr.polyprotocol.api;

import lol.pyr.polyprotocol.PacketBuffer;
import lol.pyr.polyprotocol.ProtocolState;
import lol.pyr.polyprotocol.ProtocolVersion;

public interface Packet {
    ProtocolState getProtocolState();
    ProtocolVersion getProtocolVersion();
    int getId();
    PacketBuffer writeTo(PacketBuffer buffer);
}
