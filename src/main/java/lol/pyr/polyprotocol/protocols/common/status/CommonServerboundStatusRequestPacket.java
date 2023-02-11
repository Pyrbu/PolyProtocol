package lol.pyr.polyprotocol.protocols.common.status;

import lol.pyr.polyprotocol.data.PacketBuffer;
import lol.pyr.polyprotocol.states.ProtocolState;
import lol.pyr.polyprotocol.protocols.ProtocolVersion;
import lol.pyr.polyprotocol.api.Packet;

/**
 * Packet is fully common across all versions
 */
public class CommonServerboundStatusRequestPacket implements Packet {
    private final ProtocolVersion protocolVersion;

    public CommonServerboundStatusRequestPacket(ProtocolVersion protocolVersion) {
        this.protocolVersion = protocolVersion;
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
        return buffer;
    }
}
