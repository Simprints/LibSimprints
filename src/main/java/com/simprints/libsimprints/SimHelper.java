package com.simprints.libsimprints;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * A helper class facilitating the construction of an {@link Intent} to call Simprints ID.
 * <p>
 * Two call-outs are currently available:
 * <p><ul>
 * <li> A registration call-out will collect fingerprints and link them to a new GUID.
 * The call-back data will either contain a {@link Registration} object with the GUID and the fingerprint templates, or a {@link RefusalForm} object with additional refusal data.
 * <li> An identification call-out will collect fingerprints and search through a group of people.
 * The call-back data will either contain an array of {@link Identification} objects, or a {@link RefusalForm} object with additional refusal data.
 * <li> A verify call-out will collect fingerprints and verify a specific user. The call-back will return a {@link Verification} object, or additional data such as errors and refusal data.
 * <li> (COMING SOON) An update call-out will collect new fingerprints and link them to an existing GUID.
 * </ul><p>
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class SimHelper {

    final private String projectId;
    final private String userId;

    /**
     * Constructs a new helper for making call-outs to Simprints ID with the specified project id and user id.
     *
     * @param projectId identifies the project that is making the call-out. Will be checked if it matches the scanned project id upon signing-in.
     * @param userId identifies which user is making a request to Simprints ID. Can be any arbitrary String.
     */
    public SimHelper(@NonNull String projectId, @NonNull String userId) {
        this.projectId = projectId;
        this.userId = userId;
    }

    /**
     * Builds a new {@link Intent} to make a registration call-out to Simprints ID.
     * This intent will start an activity that returns a {@link Registration} object.
     *
     * @param moduleId identifies which module to register into.
     * @return a new registration {@link Intent}.
     */
    @NonNull
    public Intent register(@NonNull String moduleId) {
        return new Intent(Constants.SIMPRINTS_REGISTER_INTENT)
                .putExtra(Constants.SIMPRINTS_PROJECT_ID, projectId)
                .putExtra(Constants.SIMPRINTS_USER_ID, userId)
                .putExtra(Constants.SIMPRINTS_MODULE_ID, moduleId);
    }

    /**
     * Builds a new {@link Intent} to make a registration call-out to Simprints ID
     * with additional arbitrary {@link Metadata}.
     * This intent will start an activity that returns a {@link Registration} object.
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
     * (COMING SOON) Builds a new {@link Intent} to make an update call-out to Simprints ID.
     *
     * @param moduleId identifies which module to update.
     * @param updateId identifies which beneficiary to update.
     * @return a new update {@link Intent}.
     */
    @NonNull
    public Intent update(@NonNull String moduleId, @NonNull String updateId) {
        return new Intent(Constants.SIMPRINTS_UPDATE_INTENT)
                .putExtra(Constants.SIMPRINTS_PROJECT_ID, projectId)
                .putExtra(Constants.SIMPRINTS_USER_ID, userId)
                .putExtra(Constants.SIMPRINTS_MODULE_ID, moduleId)
                .putExtra(Constants.SIMPRINTS_UPDATE_GUID, updateId);
    }

    /**
     * (COMING SOON) Builds a new {@link Intent} to make an update call-out to Simprints ID
     * with additional arbitrary {@link Metadata}.
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
     * This intent will start an activity that returns an ArrayList of {@link Identification} objects.
     *
     * @param moduleId identifies which module to search in.
     * @return a new identification {@link Intent}.
     */
    @NonNull
    public Intent identify(@NonNull String moduleId) {
        Intent intent = new Intent(Constants.SIMPRINTS_IDENTIFY_INTENT);
        intent.putExtra(Constants.SIMPRINTS_PROJECT_ID, projectId);
        intent.putExtra(Constants.SIMPRINTS_USER_ID, userId);
        intent.putExtra(Constants.SIMPRINTS_MODULE_ID, moduleId);
        return intent;
    }

    /**
     * Builds a new {@link Intent} to make an identification call-out to Simprints ID with
     * additional arbitrary {@link Metadata}.
     * This intent will start an activity that returns an ArrayList of {@link Identification} objects.
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
     * @return a new confirm indentity {@link Intent}.
     */
    public Intent confirmIdentity(@NonNull Context context,
                                @NonNull String sessionId,
                                @Nullable String selectedGuid) {
        Intent intent = new Intent(Constants.SIMPRINTS_SELECT_GUID_INTENT);
        intent.setPackage(Constants.SIMPRINTS_PACKAGE_NAME);
        intent.putExtra(Constants.SIMPRINTS_PROJECT_ID, projectId);
        intent.putExtra(Constants.SIMPRINTS_SESSION_ID, sessionId);
        intent.putExtra(Constants.SIMPRINTS_SELECTED_GUID, selectedGuid);
        return intent;
    }

    /**
     * Builds a new {@link Intent} to register the templates captured during
     * the last Simprints ID session, if that was an identification.
     * Used to register a new record after an identification that hasn't produced
     * valid results without capturing the templates again.
     *
     * @param moduleId identifies which module to register into.
     * @param metadata metadata to attach to the registration.
     * @param sessionId identifies the identification session.
     * @return a new registration for last biometrics {@link Intent}.
     */
    public Intent registerLastBiometrics(@NonNull String moduleId, @NonNull Metadata metadata, @NonNull String sessionId) {
        return register(moduleId, metadata)
            .putExtra(Constants.SIMPRINTS_SESSION_ID, sessionId);
    }

    /**
     * Builds a new {@link Intent} to make a verification call-out to Simprints ID.
     * This intent will start an activity that returns a {@link Verification} object.
     *
     * @param moduleId identifies which module contains the registered beneficiary to verify.
     * @param verifyId identifies which registered beneficiary to verify.
     * @return a new verification {@link Intent}.
     */
    @NonNull
    public Intent verify(@NonNull String moduleId, @NonNull String verifyId) {
        Intent intent = new Intent(Constants.SIMPRINTS_VERIFY_INTENT);
        intent.putExtra(Constants.SIMPRINTS_PROJECT_ID, projectId);
        intent.putExtra(Constants.SIMPRINTS_USER_ID, userId);
        intent.putExtra(Constants.SIMPRINTS_MODULE_ID, moduleId);
        intent.putExtra(Constants.SIMPRINTS_VERIFY_GUID, verifyId);
        return intent;
    }

    /**
     * Builds a new {@link Intent} to make a verification call-out to Simprints ID with
     * additional arbitrary {@link Metadata}.
     * This intent will start an activity that returns a {@link Verification} object.
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
