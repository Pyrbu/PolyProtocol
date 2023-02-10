package lol.pyr.polyprotocol.protocols.common.status;

import lol.pyr.polyprotocol.PacketBuffer;
import lol.pyr.polyprotocol.ProtocolState;
import lol.pyr.polyprotocol.ProtocolVersion;
import lol.pyr.polyprotocol.api.Packet;
import lombok.Getter;

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
