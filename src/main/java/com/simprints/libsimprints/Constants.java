package com.simprints.libsimprints;

import android.app.Activity;

@SuppressWarnings("unused")
public class Constants {

    // Intents
    final public static String SIMPRINTS_REGISTER_INTENT = "com.simprints.id.REGISTER";
    final public static String SIMPRINTS_IDENTIFY_INTENT = "com.simprints.id.IDENTIFY";

    // Mandatory extras
    final public static String SIMPRINTS_API_KEY = "apiKey";
    final public static String SIMPRINTS_USER_ID = "userId";
    final public static String SIMPRINTS_MODULE_ID = "moduleId";

    // Optional extras
    final public static String SIMPRINTS_CALLING_PACKAGE = "packageName";
    final public static String SIMPRINTS_UPDATE_GUID = "updateGuid";

    // Result codes
    final public static int SIMPRINTS_OK = Activity.RESULT_OK;
    final public static int SIMPRINTS_CANCELLED = Activity.RESULT_CANCELED;
    final public static int SIMPRINTS_MISSING_API_KEY = Activity.RESULT_FIRST_USER;
    final public static int SIMPRINTS_INVALID_API_KEY = Activity.RESULT_FIRST_USER + 1;
    final public static int SIMPRINTS_MISSING_USER_ID = Activity.RESULT_FIRST_USER + 2;
    final public static int SIMPRINTS_INVALID_USER_ID = Activity.RESULT_FIRST_USER + 3;
    final public static int SIMPRINTS_MISSING_MODULE_ID = Activity.RESULT_FIRST_USER + 4;
    final public static int SIMPRINTS_INVALID_MODULE_ID = Activity.RESULT_FIRST_USER + 5;
    final public static int SIMPRINTS_INVALID_INTENT_ACTION = Activity.RESULT_FIRST_USER + 6;

    // Result extras
    final public static String SIMPRINTS_REGISTRATION = "registration";
    final public static String SIMPRINTS_IDENTIFICATIONS = "identification";
    final public static String SIMPRINTS_REFUSAL_FORM = "refusalForm";
}
