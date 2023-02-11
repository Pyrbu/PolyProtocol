package lol.pyr.polyprotocol.protocols.R1_7_6;

import lol.pyr.polyprotocol.states.PacketDirection;
import lol.pyr.polyprotocol.states.ProtocolState;
import lol.pyr.polyprotocol.protocols.ProtocolVersion;
import lol.pyr.polyprotocol.api.ProtocolRouter;
import lol.pyr.polyprotocol.protocols.R1_7_2.login.R1_7_2ClientboundEncryptionRequestPacket;
import lol.pyr.polyprotocol.protocols.common.login.CommonClientboundLoginSuccessPacket;

public class R1_7_6ProtocolRouter extends ProtocolRouter {
    public R1_7_6ProtocolRouter() {
        super(ProtocolVersion.R1_7_6);
        registerDecoder(PacketDirection.CLIENTBOUND, ProtocolState.LOGIN, 0x01, buffer -> new R1_7_2ClientboundEncryptionRequestPacket(ProtocolVersion.R1_7_6, buffer));
        registerDecoder(PacketDirection.CLIENTBOUND, ProtocolState.LOGIN, 0x02, buffer -> new CommonClientboundLoginSuccessPacket(ProtocolVersion.R1_7_6, buffer));
    }
}
