package com.simprints.libsimprints;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * A helper class facilitating the construction of an {@link Intent} to call Simprints ID.
 * <p>
 * Two call-outs are currently available:
 * <p><ul>
 * <li> A registration call-out will collect fingerprints and link them to a new GUID.
 * The call-back data will either contain a {@link Registration} object with the GUID and the fingerprint templates, or a {@link RefusalForm} object with additional refusal data.
 * <li> An identification call-out will collect fingerprints and search through a group of people.
 * The call-back data will either contain an array of {@link Identification} objects, or a {@link RefusalForm} object with additional refusal data.
 * <li> (COMING SOON) A verify call-out will collect fingerprints and verify a specific user. The call-back will return the verification score, or additional data such as errors and refusal data.
 * <li> (COMING SOON) An update call-out will collect new fingerprints and link them to an existing GUID.
 * </ul><p>
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class SimHelper {

    final private String apiKey;
    final private String userId;

    /**
     * Constructs a new helper for making call-outs to Simprints ID with the specified api key and user id.
     *
     * @param apiKey used to authenticate access to Simprints ID.
     * @param userId identifies which user is making a request to Simprints ID.
     */
    public SimHelper(@NonNull String apiKey, @NonNull String userId) {
        this.apiKey = apiKey;
        this.userId = userId;
    }

    /**
     * Builds a new {@link Intent} to make a registration call-out to Simprints ID.
     *
     * @param moduleId identifies which module to register into.
     * @return a new registration {@link Intent}.
     */
    @NonNull
    public Intent register(@NonNull String moduleId) {
        return new Intent(Constants.SIMPRINTS_REGISTER_INTENT)
                .putExtra(Constants.SIMPRINTS_API_KEY, apiKey)
                .putExtra(Constants.SIMPRINTS_USER_ID, userId)
                .putExtra(Constants.SIMPRINTS_MODULE_ID, moduleId);
    }

    /**
     * Builds a new {@link Intent} to make a registration call-out to Simprints ID.
     *
     * @param moduleId identifies which module to register into.
     * @param metadata optional metadata to attach to the registration.
     * @return a new registration {@link Intent}.
     */
    @NonNull
    public Intent register(@NonNull String moduleId, @NonNull Metadata metadata) {
        return register(moduleId)
                .putExtra(Constants.SIMPRINTS_METADATA, metadata.toString());
    }

    /**
     * Builds a new {@link Intent} to make an update call-out to Simprints ID.
     *
     * @param moduleId identifies which module to update.
     * @param updateId identifies which beneficiary to update.
     * @return a new update {@link Intent}.
     */
    @NonNull
    public Intent update(@NonNull String moduleId, @NonNull String updateId) {
        return new Intent(Constants.SIMPRINTS_UPDATE_INTENT)
                .putExtra(Constants.SIMPRINTS_API_KEY, apiKey)
                .putExtra(Constants.SIMPRINTS_USER_ID, userId)
                .putExtra(Constants.SIMPRINTS_MODULE_ID, moduleId)
                .putExtra(Constants.SIMPRINTS_UPDATE_GUID, updateId);
    }

    /**
     * Builds a new {@link Intent} to make an update call-out to Simprints ID.
     *
     * @param moduleId identifies which module to update.
     * @param updateId identifies which beneficiary to update.
     * @param metadata optional metadata to attach to the update.
     * @return a new update {@link Intent}.
     */
    @NonNull
    public Intent update(@NonNull String moduleId, @NonNull String updateId, @NonNull Metadata metadata) {
        return update(moduleId, updateId)
                .putExtra(Constants.SIMPRINTS_METADATA, metadata.toString());
    }

    /**
     * Builds a new {@link Intent} to make an identification call-out to Simprints ID.
     *
     * @param moduleId identifies which module to search in.
     * @return a new identification {@link Intent}.
     */
    @NonNull
    public Intent identify(@NonNull String moduleId) {
        Intent intent = new Intent(Constants.SIMPRINTS_IDENTIFY_INTENT);
        intent.putExtra(Constants.SIMPRINTS_API_KEY, apiKey);
        intent.putExtra(Constants.SIMPRINTS_USER_ID, userId);
        intent.putExtra(Constants.SIMPRINTS_MODULE_ID, moduleId);
        return intent;
    }

    /**
     * Builds a new {@link Intent} to make an identification call-out to Simprints ID.
     *
     * @param moduleId identifies which module to search in.
     * @param metadata optional metadata to attach to the identification.
     * @return a new identification {@link Intent}.
     */
    @NonNull
    public Intent identify(@NonNull String moduleId, @NonNull Metadata metadata) {
        return identify(moduleId)
                .putExtra(Constants.SIMPRINTS_METADATA, metadata.toString());
    }

    /**
     * Sends which GUID was confirmed in a session back to SimprintsId.
     *
     * @param context context of the host app.
     * @param sessionId identifies the identification session.
     * @param selectedGuid the GUID that was confirmed in the host app.
     */
    public void confirmIdentity(@NonNull Context context,
                                @NonNull String sessionId,
                                @Nullable String selectedGuid) {
        Intent intent = new Intent(Constants.SIMPRINTS_SELECT_GUID_INTENT);
        intent.putExtra(Constants.SIMPRINTS_API_KEY, apiKey);
        intent.putExtra(Constants.SIMPRINTS_SESSION_ID, sessionId);
        intent.putExtra(Constants.SIMPRINTS_SELECTED_GUID, selectedGuid);
        context.startService(intent);
    }


    /**
     * Builds a new {@link Intent} to make a verification call-out to Simprints ID.
     *
     * @param moduleId identifies which module contains the registered beneficiary to verify.
     * @param verifyId identifies which registered beneficiary to verify.
     * @return a new verification {@link Intent}.
     */
    @NonNull
    public Intent verify(@NonNull String moduleId, @NonNull String verifyId) {
        Intent intent = new Intent(Constants.SIMPRINTS_VERIFY_INTENT);
        intent.putExtra(Constants.SIMPRINTS_API_KEY, apiKey);
        intent.putExtra(Constants.SIMPRINTS_USER_ID, userId);
        intent.putExtra(Constants.SIMPRINTS_MODULE_ID, moduleId);
        intent.putExtra(Constants.SIMPRINTS_VERIFY_GUID, verifyId);
        return intent;
    }

    /**
     * Builds a new {@link Intent} to make a verification call-out to Simprints ID.
     *
     * @param moduleId identifies which module contains the registered beneficiary to verify.
     * @param verifyId identifies which registered beneficiary to verify.
     * @param metadata optional metadata to attach to the verification.
     * @return a new verification {@link Intent}.
     */
    @NonNull
    public Intent verify(@NonNull String moduleId, @NonNull String verifyId, @NonNull Metadata metadata) {
        return verify(moduleId, verifyId)
                .putExtra(Constants.SIMPRINTS_METADATA, metadata.toString());
    }
}
