package lol.pyr.polyprotocol.protocols.R1_19_3.status;

import lol.pyr.polyprotocol.PacketBuffer;
import lol.pyr.polyprotocol.ProtocolState;
import lol.pyr.polyprotocol.ProtocolVersion;
import lol.pyr.polyprotocol.api.Packet;
import lombok.Data;

@Data
public class ServerboundPingRequestPacket implements Packet {
    private final long payload;

    @Override
    public ProtocolState getProtocolState() {
        return ProtocolState.STATUS;
    }

    @Override
    public ProtocolVersion getProtocolVersion() {
        return ProtocolVersion.R1_19_3;
    }

    @Override
    public int getId() {
        return 0x01;
    }

    public static ServerboundPingRequestPacket fromBuffer(PacketBuffer buffer) {
        return new ServerboundPingRequestPacket(buffer.readLong());
    }

    @Override
    public PacketBuffer toBuffer() {
        return new PacketBuffer().writeLong(payload);
    }
}
