package lol.pyr.polyprotocol.protocols.common.login;

import lol.pyr.polyprotocol.data.PacketBuffer;
import lol.pyr.polyprotocol.states.ProtocolState;
import lol.pyr.polyprotocol.protocols.ProtocolVersion;
import lol.pyr.polyprotocol.api.Packet;
import lombok.Getter;

import java.util.UUID;

/**
 * Note: UUID is encoded as two longs instead of string in {@link ProtocolVersion#R1_16} and upwards
 * Note: Packet contains properties in {@link ProtocolVersion#R1_19} and upwards
 */
public class CommonClientboundLoginSuccessPacket implements Packet {
    private final ProtocolVersion protocolVersion;
    @Getter private final UUID uuid;
    @Getter private final String username;


    public CommonClientboundLoginSuccessPacket(ProtocolVersion protocolVersion, UUID uuid,  String username) {
        this.protocolVersion = protocolVersion;
        this.uuid = uuid;
        this.username = username;
    }

    public CommonClientboundLoginSuccessPacket(ProtocolVersion protocolVersion, PacketBuffer buffer) {
        this(protocolVersion, UUID.fromString(buffer.readString()), buffer.readString());
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
        return 0x02;
    }

    @Override
    public PacketBuffer writeTo(PacketBuffer buffer) {
        buffer.writeString(uuid.toString());
        buffer.writeString(username);
        return buffer;
    }
}
