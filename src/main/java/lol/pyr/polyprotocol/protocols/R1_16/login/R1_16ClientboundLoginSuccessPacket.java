package lol.pyr.polyprotocol.protocols.R1_16.login;

import lol.pyr.polyprotocol.data.PacketBuffer;
import lol.pyr.polyprotocol.protocols.ProtocolVersion;
import lol.pyr.polyprotocol.protocols.common.login.CommonClientboundLoginSuccessPacket;

import java.util.UUID;

public class R1_16ClientboundLoginSuccessPacket extends CommonClientboundLoginSuccessPacket {
    public R1_16ClientboundLoginSuccessPacket(ProtocolVersion protocolVersion, UUID uuid, String username) {
        super(protocolVersion, uuid, username);
    }

    public R1_16ClientboundLoginSuccessPacket(ProtocolVersion protocolVersion, PacketBuffer buffer) {
        this(protocolVersion, buffer.readUUID(), buffer.readString());
    }
}
