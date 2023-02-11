package lol.pyr.polyprotocol.protocols.common.login;

import lol.pyr.polyprotocol.data.PacketBuffer;
import lol.pyr.polyprotocol.states.ProtocolState;
import lol.pyr.polyprotocol.protocols.ProtocolVersion;
import lol.pyr.polyprotocol.api.Packet;
import lombok.Getter;

/**
 * Packet is fully common across all versions
 */
public class CommonClientboundDisconnectPacket implements Packet {
    private final ProtocolVersion protocolVersion;
    @Getter protected final String reason;

    public CommonClientboundDisconnectPacket(ProtocolVersion protocolVersion, String reason) {
        this.protocolVersion = protocolVersion;
        this.reason = reason;
    }

    public CommonClientboundDisconnectPacket(ProtocolVersion protocolVersion, PacketBuffer buffer) {
        this(protocolVersion, buffer.readString());
    }

    @Override
    public ProtocolState getProtocolState() {
        return ProtocolState.LOGIN;
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
        buffer.writeString(reason);
        return buffer;
    }
}
