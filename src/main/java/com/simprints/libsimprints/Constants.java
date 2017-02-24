package com.simprints.libsimprints;

import android.app.Activity;

@SuppressWarnings("unused")
public class Constants {

    // Intents
    final public static String SIMPRINTS_REGISTER_INTENT = "com.simprints.id.REGISTER";
    final public static String SIMPRINTS_IDENTIFY_INTENT = "com.simprints.id.IDENTIFY";
    final public static String SIMPRINTS_UPDATE_INTENT = "com.simprints.id.UPDATE";
    final public static String SIMPRINTS_VERIFY_INTENT = "com.simprints.id.VERIFY";

    // Mandatory extras
    final public static String SIMPRINTS_API_KEY = "apiKey";
    final public static String SIMPRINTS_USER_ID = "userId";
    final public static String SIMPRINTS_MODULE_ID = "moduleId";

    // Mandatory for SIMPRINTS_UPDATE_INTENT
    final public static String SIMPRINTS_UPDATE_GUID = "updateGuid";

    // Mandatory for SIMPRINTS_VERIFY_INTENT
    final public static String SIMPRINTS_VERIFY_GUID = "verifyGuid";

    // Optional extras
    final public static String SIMPRINTS_CALLING_PACKAGE = "packageName";

    // Result codes
    final public static int SIMPRINTS_OK = Activity.RESULT_OK;
    final public static int SIMPRINTS_CANCELLED = Activity.RESULT_CANCELED;
    final public static int SIMPRINTS_MISSING_API_KEY = Activity.RESULT_FIRST_USER;
    final public static int SIMPRINTS_INVALID_API_KEY = Activity.RESULT_FIRST_USER + 1;
    final public static int SIMPRINTS_MISSING_USER_ID = Activity.RESULT_FIRST_USER + 2;
    final public static int SIMPRINTS_MISSING_MODULE_ID = Activity.RESULT_FIRST_USER + 4;
    final public static int SIMPRINTS_INVALID_INTENT_ACTION = Activity.RESULT_FIRST_USER + 6;
    final public static int SIMPRINTS_INVALID_UPDATE_GUID = Activity.RESULT_FIRST_USER + 7;
    final public static int SIMPRINTS_MISSING_UPDATE_GUID = Activity.RESULT_FIRST_USER + 8;

    // Result extras
    final public static String SIMPRINTS_REGISTRATION = "registration";
    final public static String SIMPRINTS_IDENTIFICATIONS = "identification";
    final public static String SIMPRINTS_REFUSAL_FORM = "refusalForm";
}
