package com.simprints.libsimprints;

import android.content.Intent

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
 * </ul><p>
 *
 * @param projectId identifies the project that is making the call-out. Will be checked if it matches the scanned project id upon signing-in.
 * @param userId    identifies which user is making a request to Simprints ID. Can be any arbitrary String.
 */
@SuppressWarnings("unused", "WeakerAccess")
data class SimHelper(
    private val projectId: String,
    private val userId: String
) {

    /**
     * Builds a new {@link Intent} to make a registration call-out to Simprints ID.
     * This intent will start an activity that returns a {@link Registration} object.
     *
     * @param moduleId identifies which module to register into.
     * @return a new registration {@link Intent}.
     */
    fun register(moduleId: String) = Intent(Constants.SIMPRINTS_REGISTER_INTENT)
        .putExtra(Constants.SIMPRINTS_PROJECT_ID, projectId)
        .putExtra(Constants.SIMPRINTS_USER_ID, userId)
        .putExtra(Constants.SIMPRINTS_MODULE_ID, moduleId)

    /**
     * Builds a new {@link Intent} to make a registration call-out to Simprints ID
     * with additional arbitrary {@link Metadata}.
     * This intent will start an activity that returns a {@link Registration} object.
     *
     * @param moduleId identifies which module to register into.
     * @param metadata optional metadata to attach to the registration.
     * @return a new registration {@link Intent}.
     */
    fun register(moduleId: String, metadata: Metadata) = register(moduleId)
        .putExtra(Constants.SIMPRINTS_METADATA, metadata.toString())

    /**
     * (COMING SOON) Builds a new {@link Intent} to make an update call-out to Simprints ID.
     *
     * @param moduleId identifies which module to update.
     * @param updateId identifies which beneficiary to update.
     * @return a new update {@link Intent}.
     */
    fun update(moduleId: String, updateId: String) = Intent(Constants.SIMPRINTS_UPDATE_INTENT)
        .putExtra(Constants.SIMPRINTS_PROJECT_ID, projectId)
        .putExtra(Constants.SIMPRINTS_USER_ID, userId)
        .putExtra(Constants.SIMPRINTS_MODULE_ID, moduleId)
        .putExtra(Constants.SIMPRINTS_UPDATE_GUID, updateId)

    /**
     * (COMING SOON) Builds a new {@link Intent} to make an update call-out to Simprints ID
     * with additional arbitrary {@link Metadata}.
     *
     * @param moduleId identifies which module to update.
     * @param updateId identifies which beneficiary to update.
     * @param metadata optional metadata to attach to the update.
     * @return a new update {@link Intent}.
     */
    fun update(moduleId: String, updateId: String, metadata: Metadata) = update(moduleId, updateId)
        .putExtra(Constants.SIMPRINTS_METADATA, metadata.toString());

    /**
     * Builds a new {@link Intent} to make an identification call-out to Simprints ID.
     * This intent will start an activity that returns an ArrayList of {@link Identification} objects.
     *
     * @param moduleId identifies which module to search in.
     * @return a new identification {@link Intent}.
     */
    fun identify(moduleId: String) = Intent(Constants.SIMPRINTS_IDENTIFY_INTENT)
        .putExtra(Constants.SIMPRINTS_PROJECT_ID, projectId)
        .putExtra(Constants.SIMPRINTS_USER_ID, userId)
        .putExtra(Constants.SIMPRINTS_MODULE_ID, moduleId);

    /**
     * Builds a new {@link Intent} to make an identification call-out to Simprints ID with
     * additional arbitrary {@link Metadata}.
     * This intent will start an activity that returns an ArrayList of {@link Identification} objects.
     *
     * @param moduleId identifies which module to search in.
     * @param metadata optional metadata to attach to the identification.
     * @return a new identification {@link Intent}.
     */
    fun identify(moduleId: String, metadata: Metadata) = identify(moduleId)
        .putExtra(Constants.SIMPRINTS_METADATA, metadata.toString())

    /**
     * Sends which GUID was confirmed in a session back to SimprintsId.
     *
     * @param sessionId    identifies the identification session.
     * @param selectedGuid the GUID that was confirmed in the host app.
     * @return a new confirm indentity {@link Intent}.
     */
    fun confirmIdentity(sessionId: String, selectedGuid: String) =
        Intent(Constants.SIMPRINTS_SELECT_GUID_INTENT)
            .putExtra(Constants.SIMPRINTS_PROJECT_ID, projectId)
            .putExtra(Constants.SIMPRINTS_SESSION_ID, sessionId)
            .putExtra(Constants.SIMPRINTS_SELECTED_GUID, selectedGuid)

    /**
     * Builds a new {@link Intent} to register the templates captured during
     * the last Simprints ID session, if that was an identification.
     * Used to register a new record after an identification that hasn't produced
     * valid results without capturing the templates again.
     *
     * @param moduleId  identifies which module to register into.
     * @param sessionId identifies the identification session.
     * @return a new registration for last biometrics {@link Intent}.
     */
    fun registerLastBiometrics(moduleId: String, sessionId: String) =
        Intent(Constants.SIMPRINTS_REGISTER_LAST_BIOMETRICS_INTENT)
            .putExtra(Constants.SIMPRINTS_PROJECT_ID, projectId)
            .putExtra(Constants.SIMPRINTS_USER_ID, userId)
            .putExtra(Constants.SIMPRINTS_MODULE_ID, moduleId)
            .putExtra(Constants.SIMPRINTS_SESSION_ID, sessionId)

    /**
     * Builds a new {@link Intent} to register the templates captured during
     * the last Simprints ID session, if that was an identification.
     * Used to register a new record after an identification that hasn't produced
     * valid results without capturing the templates again.
     *
     * @param moduleId  identifies which module to register into.
     * @param sessionId identifies the identification session.
     * @param metadata  metadata to attach to the registration.
     * @return a new registration for last biometrics {@link Intent}.
     */
    fun registerLastBiometrics(moduleId: String, sessionId: String, metadata: Metadata) =
        registerLastBiometrics(moduleId, sessionId)
            .putExtra(Constants.SIMPRINTS_METADATA, metadata.toString())

    /**
     * Builds a new {@link Intent} to make a verification call-out to Simprints ID.
     * This intent will start an activity that returns a {@link Verification} object.
     *
     * @param moduleId identifies which module contains the registered beneficiary to verify.
     * @param verifyId identifies which registered beneficiary to verify.
     * @return a new verification {@link Intent}.
     */
    fun verify(moduleId: String, verifyId: String) = Intent(Constants.SIMPRINTS_VERIFY_INTENT)
        .putExtra(Constants.SIMPRINTS_PROJECT_ID, projectId)
        .putExtra(Constants.SIMPRINTS_USER_ID, userId)
        .putExtra(Constants.SIMPRINTS_MODULE_ID, moduleId)
        .putExtra(Constants.SIMPRINTS_VERIFY_GUID, verifyId)

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
    fun verify(moduleId: String, verifyId: String, metadata: Metadata) = verify(moduleId, verifyId)
        .putExtra(Constants.SIMPRINTS_METADATA, metadata.toString())
}
