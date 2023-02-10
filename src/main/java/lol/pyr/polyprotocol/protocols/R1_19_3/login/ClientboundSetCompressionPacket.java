package lol.pyr.polyprotocol.protocols.R1_19_3.login;

import lol.pyr.polyprotocol.PacketBuffer;
import lol.pyr.polyprotocol.ProtocolState;
import lol.pyr.polyprotocol.ProtocolVersion;
import lol.pyr.polyprotocol.api.Packet;
import lombok.Data;

@Data
public class ClientboundSetCompressionPacket implements Packet {
    private final int threshold;

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
        return 0x03;
    }

    public ClientboundSetCompressionPacket readFrom(PacketBuffer buffer) {
        return new ClientboundSetCompressionPacket(buffer.readVarInt());
    }

    @Override
    public PacketBuffer writeTo(PacketBuffer buffer) {
        return buffer.writeVarInt(threshold);
    }
}
