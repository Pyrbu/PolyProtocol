package lol.pyr.polyprotocol.protocols.R1_19_3.login;

import lol.pyr.polyprotocol.PacketBuffer;
import lol.pyr.polyprotocol.ProtocolState;
import lol.pyr.polyprotocol.ProtocolVersion;
import lol.pyr.polyprotocol.api.Packet;
import lombok.Data;

import java.security.PublicKey;

@Data
public class ClientboundEncryptionRequestPacket implements Packet {
    private final String serverId;
    private final PublicKey publicKey;
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

    public static ClientboundEncryptionRequestPacket fromBuffer(PacketBuffer buffer) {
        return new ClientboundEncryptionRequestPacket(
                buffer.readString(),
                buffer.readPublicKey(),
                buffer.readByteArray()
        );
    }

    @Override
    public PacketBuffer toBuffer() {
        return new PacketBuffer()
                .writeString(serverId)
                .writePublicKey(publicKey)
                .writeByteArray(verifyToken);
    }
}
