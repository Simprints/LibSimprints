package com.simprints.libsimprints;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public enum FingerIdentifier {
    RIGHT_5TH_FINGER("rightFifthFinger"),
    RIGHT_4TH_FINGER("rightFourthFinger"),
    RIGHT_3RD_FINGER("rightThirdFinger"),
    RIGHT_INDEX_FINGER("rightIndexFinger"),
    RIGHT_THUMB("rightThumb"),
    LEFT_THUMB("leftThumb"),
    LEFT_INDEX_FINGER("leftIndexFinger"),
    LEFT_3RD_FINGER("leftThirdFinger"),
    LEFT_4TH_FINGER("leftFourthFinger"),
    LEFT_5TH_FINGER("leftFifthFinger");

    private String string;

    private final static Map<String, FingerIdentifier> stringToId;

    static {
        stringToId = new HashMap<>();
        for (FingerIdentifier fId : values()) {
            stringToId.put(fId.toString(), fId);
        }
    }

    FingerIdentifier(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return string;
    }

    public static FingerIdentifier parseFingerIdentifier(String s) {
        return stringToId.get(s);
    }
}