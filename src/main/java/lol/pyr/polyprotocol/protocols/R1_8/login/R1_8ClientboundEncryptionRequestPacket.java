package lol.pyr.polyprotocol.protocols.R1_8.login;

import lol.pyr.polyprotocol.data.PacketBuffer;
import lol.pyr.polyprotocol.protocols.ProtocolVersion;
import lol.pyr.polyprotocol.protocols.common.login.CommonClientboundEncryptionRequestPacket;

import java.security.PublicKey;

public class R1_8ClientboundEncryptionRequestPacket extends CommonClientboundEncryptionRequestPacket {
    public R1_8ClientboundEncryptionRequestPacket(ProtocolVersion protocolVersion, String serverId, PublicKey publicKey, byte[] verifyToken) {
        super(protocolVersion, serverId, publicKey, verifyToken);
    }

    public R1_8ClientboundEncryptionRequestPacket(ProtocolVersion protocolVersion, PacketBuffer buffer) {
        this(protocolVersion, buffer.readString(), buffer.readPublicKey(), buffer.readByteArray());
    }

    @Override
    public PacketBuffer writeTo(PacketBuffer buffer) {
        buffer.writeString(serverId);
        buffer.writePublicKey(publicKey);
        buffer.writeByteArray(verifyToken);
        return buffer;
    }
}
