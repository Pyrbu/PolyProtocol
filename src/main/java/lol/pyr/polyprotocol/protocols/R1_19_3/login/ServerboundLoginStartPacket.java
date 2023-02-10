package lol.pyr.polyprotocol.protocols.R1_19_3.login;

import lol.pyr.polyprotocol.PacketBuffer;
import lol.pyr.polyprotocol.ProtocolState;
import lol.pyr.polyprotocol.ProtocolVersion;
import lol.pyr.polyprotocol.api.Packet;
import lombok.Data;

import java.util.UUID;

@Data
public class ServerboundLoginStartPacket implements Packet {
    private final String username;
    private final UUID uuid;

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
        return 0x00;
    }

    public boolean hasUuid() {
        return uuid != null;
    }

    public static ServerboundLoginStartPacket readFrom(PacketBuffer buffer) {
        return new ServerboundLoginStartPacket(
                buffer.readString(),
                buffer.readBoolean() ? buffer.readUUID() : null
        );
    }

    @Override
    public PacketBuffer writeTo(PacketBuffer buffer) {
        buffer.writeString(username);
        buffer.writeBoolean(hasUuid());
        return buffer;
    }
}
