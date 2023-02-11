package lol.pyr.polyprotocol.api;

import lol.pyr.polyprotocol.data.PacketBuffer;
import lol.pyr.polyprotocol.states.ProtocolState;
import lol.pyr.polyprotocol.protocols.ProtocolVersion;

public interface Packet {
    ProtocolState getProtocolState();
    ProtocolVersion getProtocolVersion();
    int getId();
    PacketBuffer writeTo(PacketBuffer buffer);
}
