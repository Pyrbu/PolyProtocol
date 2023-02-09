package lol.pyr.polyprotocol.api;

import lol.pyr.polyprotocol.ProtocolVersion;

public interface Translator {
    ProtocolVersion getFromProtocol();
    ProtocolVersion getToProtocol();
    Packet translateForwards(Packet packet);
    Packet translateBackwards(Packet packet);
}
