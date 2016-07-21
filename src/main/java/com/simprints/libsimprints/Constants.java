package com.simprints.libsimprints;

import android.app.Activity;

@SuppressWarnings("unused")
public class Constants {

    // Intents
    final public static String SIMPRINTS_REGISTER_INTENT = "com.simprints.id.REGISTER";
    final public static String SIMPRINTS_IDENTIFY_INTENT = "com.simprints.id.IDENTIFY";

    // Mandatory extra
    final public static String SIMPRINTS_API_KEY = "apiKey";

    // Optionnal extras
    final public static String SIMPRINTS_USER_ID = "userId";
    final public static String SIMPRINTS_DEVICE_ID = "deviceId";
    final public static String SIMPRINTS_CALLING_PACKAGE = "packageName";
    final public static String SIMPRINTS_GUID = "guid";

    // Result codes
    final public static int SIMPRINTS_INVALID_API_KEY = Activity.RESULT_FIRST_USER;
    final public static int SIMPRINTS_INVALID_INTENT_ACTION = Activity.RESULT_FIRST_USER + 1;


    // Result extras
    final public static String SIMPRINTS_REGISTRATION = "registration";
    final public static String SIMPRINTS_IDENTIFICATIONS = "identification";

    final public static int RIGHT_INDEX = 0;
    final public static int RIGHT_THUMB = 1;
    final public static int LEFT_THUMB = 2;
    final public static int LEFT_INDEX = 3;

}
