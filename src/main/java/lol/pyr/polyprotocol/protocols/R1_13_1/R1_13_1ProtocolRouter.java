package lol.pyr.polyprotocol.protocols.R1_13_1;

import lol.pyr.polyprotocol.api.ProtocolRouter;
import lol.pyr.polyprotocol.protocols.ProtocolVersion;
import lol.pyr.polyprotocol.protocols.R1_8.login.R1_8ClientboundEncryptionRequestPacket;
import lol.pyr.polyprotocol.protocols.common.login.CommonClientboundDisconnectPacket;
import lol.pyr.polyprotocol.protocols.common.login.CommonClientboundLoginSuccessPacket;
import lol.pyr.polyprotocol.states.PacketDirection;
import lol.pyr.polyprotocol.states.ProtocolState;

public class R1_13_1ProtocolRouter extends ProtocolRouter {
    private static final ProtocolVersion version = ProtocolVersion.R1_13_1;

    public R1_13_1ProtocolRouter() {
        super(version);

        registerDecoder(PacketDirection.CLIENTBOUND, ProtocolState.LOGIN, 0x00, buffer -> new CommonClientboundDisconnectPacket(version, buffer));
        registerDecoder(PacketDirection.CLIENTBOUND, ProtocolState.LOGIN, 0x01, buffer -> new R1_8ClientboundEncryptionRequestPacket(version, buffer));
        registerDecoder(PacketDirection.CLIENTBOUND, ProtocolState.LOGIN, 0x02, buffer -> new CommonClientboundLoginSuccessPacket(version, buffer));
    }
}
