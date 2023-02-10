package lol.pyr.polyprotocol.protocols.R1_19_3.login;

import lol.pyr.polyprotocol.PacketBuffer;
import lol.pyr.polyprotocol.ProtocolState;
import lol.pyr.polyprotocol.ProtocolVersion;
import lol.pyr.polyprotocol.api.Packet;
import lombok.Data;

@Data
public class ServerboundLoginPluginResponsePacket implements Packet {
    private final int messageId;
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
        return 0x02;
    }

    public boolean hasData() {
        return data != null;
    }

    public static ServerboundLoginPluginResponsePacket readFrom(PacketBuffer buffer) {
        return new ServerboundLoginPluginResponsePacket(
                buffer.readVarInt(),
                buffer.readBoolean() ? buffer.readBytes(buffer.size()) : null
        );
    }

    @Override
    public PacketBuffer writeTo(PacketBuffer buffer) {
        buffer.writeVarInt(messageId);
        buffer.writeBoolean(hasData());
        if (hasData()) buffer.writeBytes(data);
        return buffer;
    }
}
