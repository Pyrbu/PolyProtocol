package lol.pyr.polyprotocol;

import java.util.HashMap;
import java.util.Map;

public enum ProtocolVersion {
    R1_7_2(4),
    R1_7_6(5),

    R1_8(47),
    R1_9(107),
    R1_9_1(108),
    R1_9_2(109),
    R1_9_3(110),

    R1_10(210),

    R1_11(315),
    R1_11_1(316),

    R1_12(335),
    R1_12_1(338),
    R1_12_2(340),

    R1_13(393),
    R1_13_1(401),
    R1_13_2(404),

    R1_14(477),
    R1_14_1(480),
    R1_14_2(485),
    R1_14_3(490),
    R1_14_4(498),

    R1_15(573),
    R1_15_1(575),
    R1_15_2(578),

    R1_16(735),
    R1_16_1(736),
    R1_16_2(751),
    R1_16_3(753),
    R1_16_4(754),

    R1_17(755),
    R1_17_1(756),

    R1_18(757),
    R1_18_2(758),

    R1_19(759),
    R1_19_2(760),
    R1_19_3(761);

    private final static Map<Integer, ProtocolVersion> versionLookupMap = new HashMap<>();

    static {
        for (ProtocolVersion protocolVersion : values()) versionLookupMap.put(protocolVersion.getProtocolNumber(), protocolVersion);
    }

    private final int protocolNumber;

    ProtocolVersion(int protocolNumber) {
        this.protocolNumber = protocolNumber;
    }

    public int getProtocolNumber() {
        return protocolNumber;
    }

    public static ProtocolVersion fromProtocolNumber(int protocolNumber) {
        if (!versionLookupMap.containsKey(protocolNumber)) throw new IllegalArgumentException("Unknown protocol number");
        return versionLookupMap.get(protocolNumber);
    }
}
