package lol.pyr.polyprotocol.protocols.common.status;

import lol.pyr.polyprotocol.PacketBuffer;
import lol.pyr.polyprotocol.ProtocolState;
import lol.pyr.polyprotocol.ProtocolVersion;
import lol.pyr.polyprotocol.api.Packet;

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
