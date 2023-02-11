package lol.pyr.polyprotocol.protocols.common.status;

import lol.pyr.polyprotocol.data.PacketBuffer;
import lol.pyr.polyprotocol.states.ProtocolState;
import lol.pyr.polyprotocol.protocols.ProtocolVersion;
import lol.pyr.polyprotocol.api.Packet;
import lombok.Getter;

/**
 * Packet is fully common across all versions
 */
public class CommonServerboundPingRequestPacket implements Packet {
    private final ProtocolVersion protocolVersion;
    @Getter private final long payload;

    public CommonServerboundPingRequestPacket(ProtocolVersion protocolVersion, long payload) {
        this.protocolVersion = protocolVersion;
        this.payload = payload;
    }

    public CommonServerboundPingRequestPacket(ProtocolVersion protocolVersion, PacketBuffer buffer) {
        this(protocolVersion, buffer.readLong());
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
        return 0x01;
    }

    @Override
    public PacketBuffer writeTo(PacketBuffer buffer) {
        return buffer.writeLong(payload);
    }
}
