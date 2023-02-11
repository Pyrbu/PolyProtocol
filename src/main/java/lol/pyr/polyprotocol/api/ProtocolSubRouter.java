package lol.pyr.polyprotocol.api;

import lol.pyr.polyprotocol.data.PacketBuffer;
import lol.pyr.polyprotocol.states.ProtocolState;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class ProtocolSubRouter {

    private final Function<PacketBuffer, Packet> handshakeDecoder;
    private final Map<Integer, Function<PacketBuffer, Packet>> playMap = new HashMap<>();
    private final Map<Integer, Function<PacketBuffer, Packet>> statusMap = new HashMap<>();
    private final Map<Integer, Function<PacketBuffer, Packet>> loginMap = new HashMap<>();

    public ProtocolSubRouter(Function<PacketBuffer, Packet> handshakeDecoder) {
        this.handshakeDecoder = handshakeDecoder;
    }

    // Because clientside doesn't have any incoming handshake packets
    public ProtocolSubRouter() {
        this.handshakeDecoder = null;
    }

    private Map<Integer, Function<PacketBuffer, Packet>> getMap(ProtocolState state) {
        return switch (state) {
            case HANDSHAKE -> throw new RuntimeException("Handshake state doesn't have a map!");
            case STATUS -> statusMap;
            case LOGIN -> loginMap;
            case PLAY -> playMap;
        };
    }

    public void registerDecoder(ProtocolState state, int packetId, Function<PacketBuffer, Packet> decoder) {
        getMap(state).put(packetId, decoder);
    }

    public Function<PacketBuffer, Packet> getDecoder(ProtocolState state, int packetId) {
        Function<PacketBuffer, Packet> decoder = switch (state) {
            case HANDSHAKE -> handshakeDecoder;
            case STATUS -> statusMap.get(packetId);
            case LOGIN -> loginMap.get(packetId);
            case PLAY -> playMap.get(packetId);
        };
        if (decoder == null) throw new RuntimeException("Unknown packet id!");
        return decoder;
    }
}
