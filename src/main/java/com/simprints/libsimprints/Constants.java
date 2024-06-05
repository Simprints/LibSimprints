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
    final public static String SIMPRINTS_REGISTER_LAST_BIOMETRICS_INTENT = "com.simprints.id.REGISTER_LAST_BIOMETRICS";

    // Mandatory extras
    final public static String SIMPRINTS_PROJECT_ID = "projectId";
    final public static String SIMPRINTS_USER_ID = "userId";
    final public static String SIMPRINTS_MODULE_ID = "moduleId";

    // Mandatory for SIMPRINTS_UPDATE_INTENT
    final public static String SIMPRINTS_UPDATE_GUID = "updateGuid";

    // Mandatory for SIMPRINTS_VERIFY_INTENT
    final public static String SIMPRINTS_VERIFY_GUID = "verifyGuid";

    // Mandatory for SIMPRINTS_SELECT_GUID_INTENT
    final public static String SIMPRINTS_SELECTED_GUID = "selectedGuid";
    final public static String SIMPRINTS_SESSION_ID = "sessionId";

    // Optional extras
    final public static String SIMPRINTS_CALLING_PACKAGE = "packageName";
    final public static String SIMPRINTS_BIOMETRIC_DATA_SOURCE = "biometricDataSource";
    final public static String SIMPRINTS_METADATA = "metadata";

    // Optional keys in SIMPRINTS_METADATA
    final public static String SIMPRINTS_SUBJECT_AGE = "subjectAge";

    // Custom callout parameters for particular integrations: Don't include if not needed
    final public static String SIMPRINTS_RESULT_FORMAT = "resultFormat";
    final public static String SIMPRINTS_ODK_RESULT_FORMAT_V01 = "ODKv01";
    final public static String SIMPRINTS_ODK_RESULT_FORMAT_V01_SEPARATOR = " ";

    // Result codes
    final public static int SIMPRINTS_OK = Activity.RESULT_OK;
    final public static int SIMPRINTS_CANCELLED = Activity.RESULT_CANCELED;
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
    final public static int SIMPRINTS_MISSING_PROJECT_ID = Activity.RESULT_FIRST_USER + 18;
    final public static int SIMPRINTS_INVALID_PROJECT_ID = Activity.RESULT_FIRST_USER + 19;
    final public static int SIMPRINTS_DIFFERENT_PROJECT_ID = Activity.RESULT_FIRST_USER + 20;
    final public static int SIMPRINTS_DIFFERENT_USER_ID = Activity.RESULT_FIRST_USER + 21;
    final public static int SIMPRINTS_ROOTED_DEVICE = Activity.RESULT_FIRST_USER + 22;
    final public static int SIMPRINTS_UNEXPECTED_ERROR = Activity.RESULT_FIRST_USER + 23;
    final public static int SIMPRINTS_BLUETOOTH_NOT_SUPPORTED = Activity.RESULT_FIRST_USER + 24;
    final public static int SIMPRINTS_INVALID_SELECTED_ID = Activity.RESULT_FIRST_USER + 25;
    final public static int SIMPRINTS_INVALID_SESSION_ID = Activity.RESULT_FIRST_USER + 26;
    final public static int SIMPRINTS_LOGIN_NOT_COMPLETE = Activity.RESULT_FIRST_USER + 27;
    final public static int SIMPRINTS_INVALID_STATE_FOR_INTENT_ACTION = Activity.RESULT_FIRST_USER + 28;
    final public static int SIMPRINTS_ENROLMENT_LAST_BIOMETRICS_FAILED= Activity.RESULT_FIRST_USER + 29;
    final public static int SIMPRINTS_LICENSE_MISSING = Activity.RESULT_FIRST_USER + 30;
    final public static int SIMPRINTS_LICENSE_INVALID = Activity.RESULT_FIRST_USER + 31;
    final public static int SIMPRINTS_SETUP_OFFLINE_DURING_MODALITY_DOWNLOAD = Activity.RESULT_FIRST_USER + 32;
    final public static int SIMPRINTS_SETUP_MODALITY_DOWNLOAD_CANCELLED = Activity.RESULT_FIRST_USER + 33;
    final public static int SIMPRINTS_FINGERPRINT_CONFIGURATION_ERROR = Activity.RESULT_FIRST_USER + 34;
    final public static int SIMPRINTS_FACE_CONFIGURATION_ERROR = Activity.RESULT_FIRST_USER + 35;
    final public static int SIMPRINTS_BACKEND_MAINTENANCE_ERROR = Activity.RESULT_FIRST_USER + 36;
    final public static int SIMPRINTS_PROJECT_PAUSED = Activity.RESULT_FIRST_USER + 37;
    final public static int SIMPRINTS_PROJECT_ENDING = Activity.RESULT_FIRST_USER + 38;
    final public static int SIMPRINTS_BLUETOOTH_NO_PERMISSION = Activity.RESULT_FIRST_USER + 39;
    final public static int SIMPRINTS_AGE_GROUP_NOT_SUPPORTED = Activity.RESULT_FIRST_USER + 40;

    // Result extras
    final public static String SIMPRINTS_REGISTRATION = "registration";
    final public static String SIMPRINTS_IDENTIFICATIONS = "identification";
    final public static String SIMPRINTS_VERIFICATION = "verification";
    final public static String SIMPRINTS_REFUSAL_FORM = "refusalForm";

    // When SIMPRINTS_BIOMETRICS_COMPLETE_CHECK is true, the user has completed the Simprints flow
    final public static String SIMPRINTS_BIOMETRICS_COMPLETE_CHECK = "biometricsComplete";

    // These two values represent data that could be null. They only apply to projects using cosync
    final public static String SIMPRINTS_COSYNC_EVENT = "events";
    final public static String SIMPRINTS_COSYNC_SUBJECT_ACTIONS = "subjectActions";

    // Deprecated extras
    /** @deprecated use {@link #SIMPRINTS_PROJECT_ID} instead. */
    @Deprecated
    final public static String SIMPRINTS_API_KEY = "apiKey";

    // Deprecated result codes
    /** @deprecated use {@link #SIMPRINTS_MISSING_PROJECT_ID} instead. */
    @Deprecated
    final public static int SIMPRINTS_MISSING_API_KEY = Activity.RESULT_FIRST_USER;
    /** @deprecated use {@link #SIMPRINTS_INVALID_PROJECT_ID} instead. */
    @Deprecated
    final public static int SIMPRINTS_INVALID_API_KEY = Activity.RESULT_FIRST_USER + 1;
}
