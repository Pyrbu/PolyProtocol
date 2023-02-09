package lol.pyr.polyprotocol.protocols.R1_19_3.status;

import lol.pyr.polyprotocol.PacketBuffer;
import lol.pyr.polyprotocol.ProtocolState;
import lol.pyr.polyprotocol.ProtocolVersion;
import lol.pyr.polyprotocol.api.Packet;

public class ServerboundStatusRequestPacket implements Packet {
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
        return 0x00;
    }

    public static ServerboundStatusRequestPacket fromBuffer(PacketBuffer buffer) {
        return new ServerboundStatusRequestPacket();
    }

    @Override
    public PacketBuffer toBuffer() {
        return new PacketBuffer();
    }
}
