package lol.pyr.polyprotocol.protocols.R1_19_3.handshake;

import lol.pyr.polyprotocol.PacketBuffer;
import lol.pyr.polyprotocol.ProtocolState;
import lol.pyr.polyprotocol.ProtocolVersion;
import lol.pyr.polyprotocol.api.Packet;
import lombok.Data;

@Data
public class ServerboundHandshakePacket implements Packet {
    private final String serverAddress;
    private final int serverPort;
    private final ProtocolState nextState;

    @Override
    public ProtocolState getProtocolState() {
        return ProtocolState.HANDSHAKE;
    }

    @Override
    public ProtocolVersion getProtocolVersion() {
        return ProtocolVersion.R1_19_3;
    }

    @Override
    public int getId() {
        return 0x00;
    }

    public ServerboundHandshakePacket fromBuffer(PacketBuffer buffer) {
        buffer.readInt();
        return new ServerboundHandshakePacket(
                buffer.readString(),
                buffer.readUnsignedShort(),
                ProtocolState.values()[buffer.readVarInt()]
        );
    }

    @Override
    public PacketBuffer toBuffer() {
        return new PacketBuffer()
                .writeVarInt(getProtocolVersion().getProtocolNumber())
                .writeString(serverAddress)
                .writeShort(serverPort)
                .writeInt(nextState.ordinal());
    }
}