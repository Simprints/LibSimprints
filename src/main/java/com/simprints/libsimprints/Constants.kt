package com.simprints.libsimprints;

import android.app.Activity;

@SuppressWarnings("unused", "WeakerAccess")
object Constants {

    // Intents
    const val SIMPRINTS_REGISTER_INTENT = "com.simprints.id.REGISTER";
    const val SIMPRINTS_IDENTIFY_INTENT = "com.simprints.id.IDENTIFY";
    const val SIMPRINTS_VERIFY_INTENT = "com.simprints.id.VERIFY";
    const val SIMPRINTS_SELECT_GUID_INTENT = "com.simprints.id.CONFIRM_IDENTITY";
    const val SIMPRINTS_REGISTER_LAST_BIOMETRICS_INTENT =
        "com.simprints.id.REGISTER_LAST_BIOMETRICS";

    // Mandatory extras
    const val SIMPRINTS_PROJECT_ID = "projectId";
    const val SIMPRINTS_USER_ID = "userId";
    const val SIMPRINTS_MODULE_ID = "moduleId";

    // Mandatory for SIMPRINTS_VERIFY_INTENT
    const val SIMPRINTS_VERIFY_GUID = "verifyGuid";

    // Mandatory for SIMPRINTS_SELECT_GUID_INTENT
    const val SIMPRINTS_SELECTED_GUID = "selectedGuid";
    const val SIMPRINTS_SESSION_ID = "sessionId";

    // Optional extras
    const val SIMPRINTS_CALLING_PACKAGE = "packageName";
    const val SIMPRINTS_BIOMETRIC_DATA_SOURCE = "biometricDataSource";
    const val SIMPRINTS_METADATA = "metadata";

    // Optional keys in SIMPRINTS_METADATA
    const val SIMPRINTS_SUBJECT_AGE = "subjectAge";

    // Custom callout parameters for particular integrations: Don't include if not needed
    const val SIMPRINTS_RESULT_FORMAT = "resultFormat";
    const val SIMPRINTS_ODK_RESULT_FORMAT_V01 = "ODKv01";
    const val SIMPRINTS_ODK_RESULT_FORMAT_V01_SEPARATOR = " ";

    // Result codes
    const val SIMPRINTS_OK = Activity.RESULT_OK;
    const val SIMPRINTS_CANCELLED = Activity.RESULT_CANCELED;
    const val SIMPRINTS_MISSING_USER_ID = Activity.RESULT_FIRST_USER + 2;
    const val SIMPRINTS_MISSING_MODULE_ID = Activity.RESULT_FIRST_USER + 4;
    const val SIMPRINTS_INVALID_INTENT_ACTION = Activity.RESULT_FIRST_USER + 6;
    const val SIMPRINTS_INVALID_UPDATE_GUID = Activity.RESULT_FIRST_USER + 7;
    const val SIMPRINTS_MISSING_UPDATE_GUID = Activity.RESULT_FIRST_USER + 8;
    const val SIMPRINTS_MISSING_VERIFY_GUID = Activity.RESULT_FIRST_USER + 9;
    const val SIMPRINTS_INVALID_METADATA = Activity.RESULT_FIRST_USER + 10;
    const val SIMPRINTS_VERIFY_GUID_NOT_FOUND_ONLINE = Activity.RESULT_FIRST_USER + 11;
    const val SIMPRINTS_VERIFY_GUID_NOT_FOUND_OFFLINE = Activity.RESULT_FIRST_USER + 12;
    const val SIMPRINTS_INVALID_VERIFY_GUID = Activity.RESULT_FIRST_USER + 13;
    const val SIMPRINTS_INVALID_RESULT_FORMAT = Activity.RESULT_FIRST_USER + 14;
    const val SIMPRINTS_INVALID_MODULE_ID = Activity.RESULT_FIRST_USER + 15;
    const val SIMPRINTS_INVALID_USER_ID = Activity.RESULT_FIRST_USER + 16;
    const val SIMPRINTS_INVALID_CALLING_PACKAGE = Activity.RESULT_FIRST_USER + 17;
    const val SIMPRINTS_MISSING_PROJECT_ID = Activity.RESULT_FIRST_USER + 18;
    const val SIMPRINTS_INVALID_PROJECT_ID = Activity.RESULT_FIRST_USER + 19;
    const val SIMPRINTS_DIFFERENT_PROJECT_ID = Activity.RESULT_FIRST_USER + 20;
    const val SIMPRINTS_DIFFERENT_USER_ID = Activity.RESULT_FIRST_USER + 21;
    const val SIMPRINTS_ROOTED_DEVICE = Activity.RESULT_FIRST_USER + 22;
    const val SIMPRINTS_UNEXPECTED_ERROR = Activity.RESULT_FIRST_USER + 23;
    const val SIMPRINTS_BLUETOOTH_NOT_SUPPORTED = Activity.RESULT_FIRST_USER + 24;
    const val SIMPRINTS_INVALID_SELECTED_ID = Activity.RESULT_FIRST_USER + 25;
    const val SIMPRINTS_INVALID_SESSION_ID = Activity.RESULT_FIRST_USER + 26;
    const val SIMPRINTS_LOGIN_NOT_COMPLETE = Activity.RESULT_FIRST_USER + 27;
    const val SIMPRINTS_INVALID_STATE_FOR_INTENT_ACTION = Activity.RESULT_FIRST_USER + 28;
    const val SIMPRINTS_ENROLMENT_LAST_BIOMETRICS_FAILED = Activity.RESULT_FIRST_USER + 29;
    const val SIMPRINTS_LICENSE_MISSING = Activity.RESULT_FIRST_USER + 30;
    const val SIMPRINTS_LICENSE_INVALID = Activity.RESULT_FIRST_USER + 31;
    const val SIMPRINTS_SETUP_OFFLINE_DURING_MODALITY_DOWNLOAD = Activity.RESULT_FIRST_USER + 32;
    const val SIMPRINTS_SETUP_MODALITY_DOWNLOAD_CANCELLED = Activity.RESULT_FIRST_USER + 33;
    const val SIMPRINTS_FINGERPRINT_CONFIGURATION_ERROR = Activity.RESULT_FIRST_USER + 34;
    const val SIMPRINTS_FACE_CONFIGURATION_ERROR = Activity.RESULT_FIRST_USER + 35;
    const val SIMPRINTS_BACKEND_MAINTENANCE_ERROR = Activity.RESULT_FIRST_USER + 36;
    const val SIMPRINTS_PROJECT_PAUSED = Activity.RESULT_FIRST_USER + 37;
    const val SIMPRINTS_PROJECT_ENDING = Activity.RESULT_FIRST_USER + 38;
    const val SIMPRINTS_BLUETOOTH_NO_PERMISSION = Activity.RESULT_FIRST_USER + 39;
    const val SIMPRINTS_AGE_GROUP_NOT_SUPPORTED = Activity.RESULT_FIRST_USER + 40;

    // Result extras
    const val SIMPRINTS_REGISTRATION = "registration";
    const val SIMPRINTS_IDENTIFICATIONS = "identification";
    const val SIMPRINTS_VERIFICATION = "verification";
    const val SIMPRINTS_VERIFICATION_SUCCESS = "verificationSuccess";
    const val SIMPRINTS_REFUSAL_FORM = "refusalForm";

    // When SIMPRINTS_BIOMETRICS_COMPLETE_CHECK is true, the user has completed the Simprints flow
    const val SIMPRINTS_BIOMETRICS_COMPLETE_CHECK = "biometricsComplete";

    // These two values represent data that could be null. They only apply to projects using cosync
    const val SIMPRINTS_COSYNC_EVENT = "events";
    const val SIMPRINTS_COSYNC_SUBJECT_ACTIONS = "subjectActions";

    // Deprecated extras
    @Deprecated("Use {@link #SIMPRINTS_PROJECT_ID} instead.")
    const val SIMPRINTS_API_KEY = "apiKey";

    // Deprecated result codes
    @Deprecated("Use {@link #SIMPRINTS_MISSING_PROJECT_ID} instead.")
    const val SIMPRINTS_MISSING_API_KEY = Activity.RESULT_FIRST_USER;

    @Deprecated("Use {@link #SIMPRINTS_INVALID_PROJECT_ID} instead.")
    const val SIMPRINTS_INVALID_API_KEY = Activity.RESULT_FIRST_USER + 1;
}
