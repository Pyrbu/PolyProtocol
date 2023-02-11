package lol.pyr.polyprotocol.states;

import lol.pyr.polyprotocol.protocols.ProtocolVersion;
import lombok.Data;

@Data
public class ConnectionState {
    private final ProtocolVersion version;
    private final ProtocolState state;
}
