package lol.pyr.polyprotocol.protocols;

import lol.pyr.polyprotocol.api.ProtocolRouter;
import lol.pyr.polyprotocol.protocols.R1_10.R1_10ProtocolRouter;
import lol.pyr.polyprotocol.protocols.R1_11.R1_11ProtocolRouter;
import lol.pyr.polyprotocol.protocols.R1_11_1.R1_11_1ProtocolRouter;
import lol.pyr.polyprotocol.protocols.R1_12.R1_12ProtocolRouter;
import lol.pyr.polyprotocol.protocols.R1_12_1.R1_12_1ProtocolRouter;
import lol.pyr.polyprotocol.protocols.R1_12_2.R1_12_2ProtocolRouter;
import lol.pyr.polyprotocol.protocols.R1_13.R1_13ProtocolRouter;
import lol.pyr.polyprotocol.protocols.R1_13_1.R1_13_1ProtocolRouter;
import lol.pyr.polyprotocol.protocols.R1_13_2.R1_13_2ProtocolRouter;
import lol.pyr.polyprotocol.protocols.R1_14.R1_14ProtocolRouter;
import lol.pyr.polyprotocol.protocols.R1_14_1.R1_14_1ProtocolRouter;
import lol.pyr.polyprotocol.protocols.R1_14_2.R1_14_2ProtocolRouter;
import lol.pyr.polyprotocol.protocols.R1_14_3.R1_14_3ProtocolRouter;
import lol.pyr.polyprotocol.protocols.R1_14_4.R1_14_4ProtocolRouter;
import lol.pyr.polyprotocol.protocols.R1_15.R1_15ProtocolRouter;
import lol.pyr.polyprotocol.protocols.R1_15_1.R1_15_1ProtocolRouter;
import lol.pyr.polyprotocol.protocols.R1_15_2.R1_15_2ProtocolRouter;
import lol.pyr.polyprotocol.protocols.R1_16.R1_16ProtocolRouter;
import lol.pyr.polyprotocol.protocols.R1_16_1.R1_16_1ProtocolRouter;
import lol.pyr.polyprotocol.protocols.R1_16_2.R1_16_2ProtocolRouter;
import lol.pyr.polyprotocol.protocols.R1_16_3.R1_16_3ProtocolRouter;
import lol.pyr.polyprotocol.protocols.R1_16_4.R1_16_4ProtocolRouter;
import lol.pyr.polyprotocol.protocols.R1_17.R1_17ProtocolRouter;
import lol.pyr.polyprotocol.protocols.R1_17_1.R1_17_1ProtocolRouter;
import lol.pyr.polyprotocol.protocols.R1_18.R1_18ProtocolRouter;
import lol.pyr.polyprotocol.protocols.R1_18_2.R1_18_2ProtocolRouter;
import lol.pyr.polyprotocol.protocols.R1_19.R1_19ProtocolRouter;
import lol.pyr.polyprotocol.protocols.R1_19_2.R1_19_2ProtocolRouter;
import lol.pyr.polyprotocol.protocols.R1_19_3.R1_19_3ProtocolRouter;
import lol.pyr.polyprotocol.protocols.R1_7_2.R1_7_2ProtocolRouter;
import lol.pyr.polyprotocol.protocols.R1_7_6.R1_7_6ProtocolRouter;
import lol.pyr.polyprotocol.protocols.R1_8.R1_8ProtocolRouter;
import lol.pyr.polyprotocol.protocols.R1_9.R1_9ProtocolRouter;
import lol.pyr.polyprotocol.protocols.R1_9_1.R1_9_1ProtocolRouter;
import lol.pyr.polyprotocol.protocols.R1_9_2.R1_9_2ProtocolRouter;
import lol.pyr.polyprotocol.protocols.R1_9_3.R1_9_3ProtocolRouter;
import lol.pyr.polyprotocol.util.LazyLoader;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public enum ProtocolVersion {
    R1_7_2(4, new LazyLoader<>(R1_7_2ProtocolRouter::new)),
    R1_7_6(5, new LazyLoader<>(R1_7_6ProtocolRouter::new)),

    R1_8(47, new LazyLoader<>(R1_8ProtocolRouter::new)),

    R1_9(107, new LazyLoader<>(R1_9ProtocolRouter::new)),
    R1_9_1(108, new LazyLoader<>(R1_9_1ProtocolRouter::new)),
    R1_9_2(109, new LazyLoader<>(R1_9_2ProtocolRouter::new)),
    R1_9_3(110, new LazyLoader<>(R1_9_3ProtocolRouter::new)),

    R1_10(210, new LazyLoader<>(R1_10ProtocolRouter::new)),

    R1_11(315, new LazyLoader<>(R1_11ProtocolRouter::new)),
    R1_11_1(316, new LazyLoader<>(R1_11_1ProtocolRouter::new)),

    R1_12(335, new LazyLoader<>(R1_12ProtocolRouter::new)),
    R1_12_1(338, new LazyLoader<>(R1_12_1ProtocolRouter::new)),
    R1_12_2(340, new LazyLoader<>(R1_12_2ProtocolRouter::new)),

    R1_13(393, new LazyLoader<>(R1_13ProtocolRouter::new)),
    R1_13_1(401, new LazyLoader<>(R1_13_1ProtocolRouter::new)),
    R1_13_2(404, new LazyLoader<>(R1_13_2ProtocolRouter::new)),

    R1_14(477, new LazyLoader<>(R1_14ProtocolRouter::new)),
    R1_14_1(480, new LazyLoader<>(R1_14_1ProtocolRouter::new)),
    R1_14_2(485, new LazyLoader<>(R1_14_2ProtocolRouter::new)),
    R1_14_3(490, new LazyLoader<>(R1_14_3ProtocolRouter::new)),
    R1_14_4(498, new LazyLoader<>(R1_14_4ProtocolRouter::new)),

    R1_15(573, new LazyLoader<>(R1_15ProtocolRouter::new)),
    R1_15_1(575, new LazyLoader<>(R1_15_1ProtocolRouter::new)),
    R1_15_2(578, new LazyLoader<>(R1_15_2ProtocolRouter::new)),

    R1_16(735, new LazyLoader<>(R1_16ProtocolRouter::new)),
    R1_16_1(736, new LazyLoader<>(R1_16_1ProtocolRouter::new)),
    R1_16_2(751, new LazyLoader<>(R1_16_2ProtocolRouter::new)),
    R1_16_3(753, new LazyLoader<>(R1_16_3ProtocolRouter::new)),
    R1_16_4(754, new LazyLoader<>(R1_16_4ProtocolRouter::new)),

    R1_17(755, new LazyLoader<>(R1_17ProtocolRouter::new)),
    R1_17_1(756, new LazyLoader<>(R1_17_1ProtocolRouter::new)),

    R1_18(757, new LazyLoader<>(R1_18ProtocolRouter::new)),
    R1_18_2(758, new LazyLoader<>(R1_18_2ProtocolRouter::new)),

    R1_19(759, new LazyLoader<>(R1_19ProtocolRouter::new)),
    R1_19_2(760, new LazyLoader<>(R1_19_2ProtocolRouter::new)),
    R1_19_3(761, new LazyLoader<>(R1_19_3ProtocolRouter::new));

    private final static Map<Integer, ProtocolVersion> versionLookupMap = new HashMap<>();

    static {
        for (ProtocolVersion protocolVersion : values()) versionLookupMap.put(protocolVersion.getProtocolNumber(), protocolVersion);
    }

    @Getter private final int protocolNumber;
    private final LazyLoader<ProtocolRouter> protocolRouter;

    ProtocolVersion(int protocolNumber, LazyLoader<ProtocolRouter> protocolRouter) {
        this.protocolNumber = protocolNumber;
        this.protocolRouter = protocolRouter;
    }

    public ProtocolRouter getProtocolRouter() {
        return protocolRouter.get();
    }

    public static ProtocolVersion fromProtocolNumber(int protocolNumber) {
        if (!versionLookupMap.containsKey(protocolNumber)) throw new IllegalArgumentException("Unknown protocol number");
        return versionLookupMap.get(protocolNumber);
    }
}
