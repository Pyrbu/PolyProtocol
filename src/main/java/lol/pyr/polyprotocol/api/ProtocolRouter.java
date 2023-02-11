package lol.pyr.polyprotocol.api;

import lol.pyr.polyprotocol.data.PacketBuffer;
import lol.pyr.polyprotocol.protocols.ProtocolVersion;
import lol.pyr.polyprotocol.protocols.common.handshake.CommonServerboundHandshakePacket;
import lol.pyr.polyprotocol.protocols.common.status.CommonClientboundPingResponsePacket;
import lol.pyr.polyprotocol.protocols.common.status.CommonClientboundStatusResponsePacket;
import lol.pyr.polyprotocol.protocols.common.status.CommonServerboundPingRequestPacket;
import lol.pyr.polyprotocol.protocols.common.status.CommonServerboundStatusRequestPacket;
import lol.pyr.polyprotocol.states.PacketDirection;
import lol.pyr.polyprotocol.states.ProtocolState;

import java.util.function.Function;

public abstract class ProtocolRouter {
    private final ProtocolSubRouter serverboundRouter;
    private final ProtocolSubRouter clientboundRouter;

    public ProtocolRouter(ProtocolVersion protocolVersion) {
        // Register all STATUS packets since they're the same on all versions
        this.clientboundRouter = new ProtocolSubRouter();
        this.clientboundRouter.registerDecoder(ProtocolState.STATUS, 0x00, buffer -> new CommonClientboundStatusResponsePacket(protocolVersion, buffer));
        this.clientboundRouter.registerDecoder(ProtocolState.STATUS, 0x01, buffer -> new CommonClientboundPingResponsePacket(protocolVersion, buffer));

        this.serverboundRouter = new ProtocolSubRouter(buffer -> new CommonServerboundHandshakePacket(protocolVersion, buffer));
        this.serverboundRouter.registerDecoder(ProtocolState.STATUS, 0x00, buffer -> new CommonServerboundStatusRequestPacket(protocolVersion));
        this.serverboundRouter.registerDecoder(ProtocolState.STATUS, 0x01, buffer -> new CommonServerboundPingRequestPacket(protocolVersion, buffer));
    }

    private ProtocolSubRouter getRouter(PacketDirection direction) {
        return switch (direction) {
            case SERVERBOUND -> serverboundRouter;
            case CLIENTBOUND -> clientboundRouter;
        };
    }

    protected void registerDecoder(PacketDirection direction, ProtocolState state, int packetId, Function<PacketBuffer, Packet> decoder) {
        getRouter(direction).registerDecoder(state, packetId, decoder);
    }

    public Packet decodePacket(PacketDirection direction, ProtocolState state, int packetId, PacketBuffer buffer) {
        return getRouter(direction).getDecoder(state, packetId).apply(buffer);
    }
}
