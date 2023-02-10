package lol.pyr.polyprotocol.protocols.R1_19_3.login;

import lol.pyr.polyprotocol.PacketBuffer;
import lol.pyr.polyprotocol.ProtocolState;
import lol.pyr.polyprotocol.ProtocolVersion;
import lol.pyr.polyprotocol.api.Packet;
import lombok.Data;

@Data
public class ClientboundLoginPluginRequestPacket implements Packet {
    private final int messageId;
    private final String channel;
    private final byte[] data;

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
        return 0x04;
    }

    public ClientboundLoginPluginRequestPacket readFrom(PacketBuffer buffer) {
        return new ClientboundLoginPluginRequestPacket(
                buffer.readVarInt(),
                buffer.readString(),
                buffer.readBytes(buffer.size())
        );
    }

    @Override
    public PacketBuffer writeTo(PacketBuffer buffer) {
        buffer.writeVarInt(messageId);
        buffer.writeString(channel);
        buffer.writeBytes(data);
        return buffer;
    }
}
