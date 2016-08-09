package com.simprints.libsimprints;

public enum FingerIdentifier {
    RIGHT_5TH_FINGER,
    RIGHT_4TH_FINGER,
    RIGHT_3RD_FINGER,
    RIGHT_INDEX_FINGER,
    RIGHT_THUMB,
    LEFT_THUMB,
    LEFT_INDEX_FINGER,
    LEFT_3RD_FINGER,
    LEFT_4TH_FINGER,
    LEFT_5TH_FINGER;

    @Override
    public String toString() {
        switch (this) {
            case RIGHT_5TH_FINGER:
                return "rightFifthFinger";
            case RIGHT_4TH_FINGER:
                return "rightFourthFinger";
            case RIGHT_3RD_FINGER:
                return "rightThirdFinger";
            case RIGHT_INDEX_FINGER:
                return "rightIndexFinger";
            case RIGHT_THUMB:
                return "rightThumb";
            case LEFT_THUMB:
                return "leftThumb";
            case LEFT_INDEX_FINGER:
                return "leftIndexFinger";
            case LEFT_3RD_FINGER:
                return "leftThirdFinger";
            case LEFT_4TH_FINGER:
                return "leftFourthFinger";
            case LEFT_5TH_FINGER:
                return "leftFifthFinger";
            default:
                throw new IllegalArgumentException();
        }
    }
}