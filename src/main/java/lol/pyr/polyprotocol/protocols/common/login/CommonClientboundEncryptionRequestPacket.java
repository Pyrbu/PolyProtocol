package lol.pyr.polyprotocol.protocols.common.login;

import lol.pyr.polyprotocol.data.PacketBuffer;
import lol.pyr.polyprotocol.states.ProtocolState;
import lol.pyr.polyprotocol.protocols.ProtocolVersion;
import lol.pyr.polyprotocol.api.Packet;
import lombok.Getter;

import java.security.PublicKey;

/**
 * Note: Packet decoding is different in {@link ProtocolVersion#R1_7_2} and {@link ProtocolVersion#R1_7_6}
 */
public class CommonClientboundEncryptionRequestPacket implements Packet {
    private final ProtocolVersion protocolVersion;
    @Getter protected final String serverId;
    @Getter protected final PublicKey publicKey;
    @Getter protected final byte[] verifyToken;

    public CommonClientboundEncryptionRequestPacket(ProtocolVersion protocolVersion, String serverId, PublicKey publicKey, byte[] verifyToken) {
        this.protocolVersion = protocolVersion;
        this.serverId = serverId;
        this.publicKey = publicKey;
        this.verifyToken = verifyToken;
    }

    public CommonClientboundEncryptionRequestPacket(ProtocolVersion protocolVersion, PacketBuffer buffer) {
        this(protocolVersion, buffer.readString(), buffer.readShortLengthPublicKey(), buffer.readShortLengthByteArray());
    }

    @Override
    public ProtocolState getProtocolState() {
        return ProtocolState.LOGIN;
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
        buffer.writeString(serverId);
        buffer.writeShortLengthPublicKey(publicKey);
        buffer.writeShortLengthByteArray(verifyToken);
        return buffer;
    }
}
