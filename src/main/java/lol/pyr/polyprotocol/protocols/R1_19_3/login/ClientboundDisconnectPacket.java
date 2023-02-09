package lol.pyr.polyprotocol.protocols.R1_19_3.login;

import lol.pyr.polyprotocol.PacketBuffer;
import lol.pyr.polyprotocol.ProtocolState;
import lol.pyr.polyprotocol.ProtocolVersion;
import lol.pyr.polyprotocol.api.Packet;
import lombok.Data;

@Data
public class ClientboundDisconnectPacket implements Packet {
    private final String reason;

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

    public static ClientboundDisconnectPacket fromBuffer(PacketBuffer buffer) {
        return new ClientboundDisconnectPacket(buffer.readString());
    }

    @Override
    public PacketBuffer toBuffer() {
        return new PacketBuffer().writeString(reason);
    }
}
