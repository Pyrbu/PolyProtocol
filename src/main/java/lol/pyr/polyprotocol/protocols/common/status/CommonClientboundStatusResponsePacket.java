package lol.pyr.polyprotocol.protocols.common.status;

import lol.pyr.polyprotocol.data.PacketBuffer;
import lol.pyr.polyprotocol.states.ProtocolState;
import lol.pyr.polyprotocol.protocols.ProtocolVersion;
import lol.pyr.polyprotocol.api.Packet;
import lombok.Getter;

/**
 * Packet is fully common across all versions
 */
public class CommonClientboundStatusResponsePacket implements Packet {
    private final ProtocolVersion protocolVersion;
    @Getter private final String json;

    public CommonClientboundStatusResponsePacket(ProtocolVersion protocolVersion, String json) {
        this.protocolVersion = protocolVersion;
        this.json = json;
    }

    public CommonClientboundStatusResponsePacket(ProtocolVersion protocolVersion, PacketBuffer buffer) {
        this(protocolVersion, buffer.readString());
    }

    @Override
    public ProtocolState getProtocolState() {
        return ProtocolState.STATUS;
    }

    @Override
    public ProtocolVersion getProtocolVersion() {
        return protocolVersion;
    }

    @Override
    public int getId() {
        return 0x00;
    }

    @Override
    public PacketBuffer writeTo(PacketBuffer buffer) {
        return buffer.writeString(json);
    }
}
