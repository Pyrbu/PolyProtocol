package lol.pyr.polyprotocol.protocols.R1_7_2;

import lol.pyr.polyprotocol.api.ProtocolRouter;
import lol.pyr.polyprotocol.protocols.ProtocolVersion;
import lol.pyr.polyprotocol.protocols.common.login.CommonClientboundDisconnectPacket;
import lol.pyr.polyprotocol.protocols.common.login.CommonClientboundEncryptionRequestPacket;
import lol.pyr.polyprotocol.protocols.common.login.CommonClientboundLoginSuccessPacket;
import lol.pyr.polyprotocol.states.PacketDirection;
import lol.pyr.polyprotocol.states.ProtocolState;

public class R1_7_2ProtocolRouter extends ProtocolRouter {
    private static final ProtocolVersion version = ProtocolVersion.R1_7_2;

    public R1_7_2ProtocolRouter() {
        super(version);

        registerDecoder(PacketDirection.CLIENTBOUND, ProtocolState.LOGIN, 0x00, buffer -> new CommonClientboundDisconnectPacket(version, buffer));
        registerDecoder(PacketDirection.CLIENTBOUND, ProtocolState.LOGIN, 0x01, buffer -> new CommonClientboundEncryptionRequestPacket(version, buffer));
        registerDecoder(PacketDirection.CLIENTBOUND, ProtocolState.LOGIN, 0x02, buffer -> new CommonClientboundLoginSuccessPacket(version, buffer));
    }
}
