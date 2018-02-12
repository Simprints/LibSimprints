package com.simprints.libsimprints;

import android.app.Activity;

@SuppressWarnings({"unused", "WeakerAccess"})
public class Constants {

    // Intents
    final public static String SIMPRINTS_REGISTER_INTENT = "com.simprints.id.REGISTER";
    final public static String SIMPRINTS_IDENTIFY_INTENT = "com.simprints.id.IDENTIFY";
    final public static String SIMPRINTS_UPDATE_INTENT = "com.simprints.id.UPDATE";
    final public static String SIMPRINTS_VERIFY_INTENT = "com.simprints.id.VERIFY";
    final public static String SIMPRINTS_SELECT_GUID_INTENT = "com.simprints.id.CONFIRM_IDENTITY";

    // Mandatory extras
    final public static String SIMPRINTS_API_KEY = "apiKey";
    final public static String SIMPRINTS_PROJECT_ID = "projectId";
    final public static String SIMPRINTS_USER_ID = "userId";
    final public static String SIMPRINTS_MODULE_ID = "moduleId";

    // Mandatory for SIMPRINTS_UPDATE_INTENT
    final public static String SIMPRINTS_UPDATE_GUID = "updateGuid";

    // Mandatory for SIMPRINTS_VERIFY_INTENT
    final public static String SIMPRINTS_VERIFY_GUID = "verifyGuid";

    // Mandatory for SIMPRINTS_SELECT_GUID_INTENT
    final public static String SIMPRINTS_PACKAGE_NAME = "com.simprints.id";
    final public static String SIMPRINTS_SELECTED_GUID = "selectedGuid";
    final public static String SIMPRINTS_SESSION_ID = "sessionId";

    // Optional extras
    final public static String SIMPRINTS_CALLING_PACKAGE = "packageName";
    final public static String SIMPRINTS_METADATA = "metadata";

    // Custom callout parameters for particular integrations: Don't include if not needed
    final public static String SIMPRINTS_RESULT_FORMAT = "resultFormat";
    final public static String SIMPRINTS_ODK_RESULT_FORMAT_V01 = "ODKv01";
    final public static String SIMPRINTS_ODK_RESULT_FORMAT_V01_SEPARATOR = " ";

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
    final public static int SIMPRINTS_MISSING_VERIFY_GUID = Activity.RESULT_FIRST_USER + 9;
    final public static int SIMPRINTS_INVALID_METADATA = Activity.RESULT_FIRST_USER + 10;
    final public static int SIMPRINTS_VERIFY_GUID_NOT_FOUND_ONLINE = Activity.RESULT_FIRST_USER + 11;
    final public static int SIMPRINTS_VERIFY_GUID_NOT_FOUND_OFFLINE = Activity.RESULT_FIRST_USER + 12;
    final public static int SIMPRINTS_INVALID_VERIFY_GUID = Activity.RESULT_FIRST_USER + 13;
    final public static int SIMPRINTS_INVALID_RESULT_FORMAT = Activity.RESULT_FIRST_USER + 14;
    final public static int SIMPRINTS_INVALID_MODULE_ID = Activity.RESULT_FIRST_USER + 15;
    final public static int SIMPRINTS_INVALID_USER_ID = Activity.RESULT_FIRST_USER + 16;
    final public static int SIMPRINTS_INVALID_CALLING_PACKAGE = Activity.RESULT_FIRST_USER + 17;
    final public static int SIMPRINTS_INVALID_PROJECT_ID = Activity.RESULT_FIRST_USER + 18;

    // Result extras
    final public static String SIMPRINTS_REGISTRATION = "registration";
    final public static String SIMPRINTS_IDENTIFICATIONS = "identification";
    final public static String SIMPRINTS_VERIFICATION = "verification";
    final public static String SIMPRINTS_REFUSAL_FORM = "refusalForm";
}
