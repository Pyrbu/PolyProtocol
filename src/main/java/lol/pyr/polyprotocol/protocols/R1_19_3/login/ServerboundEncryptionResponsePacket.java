package lol.pyr.polyprotocol.protocols.R1_19_3.login;

import lol.pyr.polyprotocol.PacketBuffer;
import lol.pyr.polyprotocol.ProtocolState;
import lol.pyr.polyprotocol.ProtocolVersion;
import lol.pyr.polyprotocol.api.Packet;
import lombok.Data;

@Data
public class ServerboundEncryptionResponsePacket implements Packet {
    private final byte[] sharedSecret;
    private final byte[] verifyToken;

    @Override
    public ProtocolState getProtocolState() {
        return ProtocolState.LOGIN;
    }

    @Override
    public ProtocolVersion getProtocolVersion() {
        return ProtocolVersion.R1_19_3;
    }

    @Override
    public int getId() {
        return 0x01;
    }

    public ServerboundEncryptionResponsePacket readFrom(PacketBuffer buffer) {
        return new ServerboundEncryptionResponsePacket(
                buffer.readByteArray(),
                buffer.readByteArray()
        );
    }

    @Override
    public PacketBuffer writeTo(PacketBuffer buffer) {
        buffer.writeByteArray(sharedSecret);
        buffer.writeByteArray(verifyToken);
        return buffer;
    }
}
