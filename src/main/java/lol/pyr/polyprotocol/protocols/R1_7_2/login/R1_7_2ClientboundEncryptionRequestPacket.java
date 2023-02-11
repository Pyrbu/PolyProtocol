package lol.pyr.polyprotocol.protocols.R1_7_2.login;

import lol.pyr.polyprotocol.data.PacketBuffer;
import lol.pyr.polyprotocol.protocols.ProtocolVersion;
import lol.pyr.polyprotocol.protocols.common.login.CommonClientboundEncryptionRequestPacket;

import java.security.PublicKey;

public class R1_7_2ClientboundEncryptionRequestPacket extends CommonClientboundEncryptionRequestPacket {
    public R1_7_2ClientboundEncryptionRequestPacket(ProtocolVersion protocolVersion, String serverId, PublicKey publicKey, byte[] verifyToken) {
        super(protocolVersion, serverId, publicKey, verifyToken);
    }

    public R1_7_2ClientboundEncryptionRequestPacket(ProtocolVersion protocolVersion, PacketBuffer buffer) {
        this(protocolVersion, buffer.readString(), buffer.readShortLengthPublicKey(), buffer.readShortLengthByteArray());
    }
}
