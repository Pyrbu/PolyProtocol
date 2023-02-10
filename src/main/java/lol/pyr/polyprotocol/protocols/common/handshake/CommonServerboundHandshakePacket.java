package lol.pyr.polyprotocol.protocols.common.handshake;

import lol.pyr.polyprotocol.PacketBuffer;
import lol.pyr.polyprotocol.ProtocolState;
import lol.pyr.polyprotocol.ProtocolVersion;
import lol.pyr.polyprotocol.api.Packet;
import lombok.Getter;

public class CommonServerboundHandshakePacket implements Packet {
    private final ProtocolVersion protocolVersion;

    @Getter private final String serverAddress;
    @Getter private final int serverPort;
    @Getter private final ProtocolState nextState;

    public CommonServerboundHandshakePacket(ProtocolVersion protocolVersion, String serverAddress, int serverPort, ProtocolState nextState) {
        this.protocolVersion = protocolVersion;
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
        this.nextState = nextState;
    }

    public CommonServerboundHandshakePacket(ProtocolVersion protocolVersion, PacketBuffer buffer) {
        if (buffer.readInt() != getProtocolVersion().getProtocolNumber()) throw new RuntimeException("Wrong protocol version used!");
        this.protocolVersion = protocolVersion;
        this.serverAddress = buffer.readString();
        this.serverPort = buffer.readUnsignedShort();
        this.nextState = ProtocolState.values()[buffer.readVarInt()];
    }

    @Override
    public ProtocolState getProtocolState() {
        return ProtocolState.HANDSHAKE;
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
        buffer.writeVarInt(getProtocolVersion().getProtocolNumber());
        buffer.writeString(serverAddress);
        buffer.writeShort(serverPort);
        buffer.writeInt(nextState.ordinal());
        return buffer;
    }
}
