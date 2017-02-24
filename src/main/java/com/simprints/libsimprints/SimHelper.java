package com.simprints.libsimprints;

import android.content.Intent;

public class SimHelper {
    private String apiKey;
    private String userId;

    public SimHelper(String apiKey, String userId) {
        this.apiKey = apiKey;
        this.userId = userId;
    }

    public Intent register(String moduleId) {
        Intent intent = new Intent(Constants.SIMPRINTS_REGISTER_INTENT);
        intent.putExtra(Constants.SIMPRINTS_API_KEY, apiKey);
        intent.putExtra(Constants.SIMPRINTS_USER_ID, userId);
        intent.putExtra(Constants.SIMPRINTS_MODULE_ID, moduleId);
        return intent;
    }

    public Intent update(String moduleId, String updateId) {
        Intent intent = new Intent(Constants.SIMPRINTS_UPDATE_INTENT);
        intent.putExtra(Constants.SIMPRINTS_API_KEY, apiKey);
        intent.putExtra(Constants.SIMPRINTS_USER_ID, userId);
        intent.putExtra(Constants.SIMPRINTS_MODULE_ID, moduleId);
        intent.putExtra(Constants.SIMPRINTS_UPDATE_GUID, updateId);
        return intent;
    }

    public Intent identify(String moduleId) {
        Intent intent = new Intent(Constants.SIMPRINTS_IDENTIFY_INTENT);
        intent.putExtra(Constants.SIMPRINTS_API_KEY, apiKey);
        intent.putExtra(Constants.SIMPRINTS_USER_ID, userId);
        intent.putExtra(Constants.SIMPRINTS_MODULE_ID, moduleId);
        return intent;
    }

    public Intent verify(String moduleId, String verifyId) {
        Intent intent = new Intent(Constants.SIMPRINTS_VERIFY_INTENT);
        intent.putExtra(Constants.SIMPRINTS_API_KEY, apiKey);
        intent.putExtra(Constants.SIMPRINTS_USER_ID, userId);
        intent.putExtra(Constants.SIMPRINTS_MODULE_ID, moduleId);
        intent.putExtra(Constants.SIMPRINTS_VERIFY_GUID, verifyId);
        return intent;
    }

}
