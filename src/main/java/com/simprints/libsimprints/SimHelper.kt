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
@Deprecated("Use SimprintsContract instead")
data class SimHelper(
    private val projectId: String,
    private val userId: String
) {

    /**
     * Builds a new {@link Intent} to make a registration call-out to Simprints ID.
     * This intent will start an activity that returns a {@link Registration} object.
     *
     * @param moduleId identifies which module to register into.
     * @param metadata optional metadata to attach to the registration if provided.
     * @return a new registration {@link Intent}.
     */
    @Deprecated("Use SimprintsContract and SimprintsRequest.Enrol instead")
    @JvmOverloads
    fun register(
        moduleId: String,
        metadata: Metadata? = null,
    ) = createBaseIntent(Constants.SIMPRINTS_REGISTER_INTENT)
        .putExtra(Constants.SIMPRINTS_MODULE_ID, moduleId)
        .appendOptionalMetadata(metadata)

    /**
     * Builds a new {@link Intent} to make an identification call-out to Simprints ID.
     * This intent will start an activity that returns an ArrayList of {@link Identification} objects.
     *
     * @param moduleId identifies which module to search in.
     * @param metadata optional metadata to attach to the identification if provided.
     * @return a new identification {@link Intent}.
     */
    @Deprecated("Use SimprintsContract and SimprintsRequest.Identify instead")
    @JvmOverloads
    fun identify(
        moduleId: String,
        metadata: Metadata? = null,
    ) = createBaseIntent(Constants.SIMPRINTS_IDENTIFY_INTENT)
        .putExtra(Constants.SIMPRINTS_MODULE_ID, moduleId)
        .appendOptionalMetadata(metadata)

    /**
     * Builds a new {@link Intent} to make a verification call-out to Simprints ID.
     * This intent will start an activity that returns a {@link Verification} object.
     *
     * @param moduleId identifies which module contains the registered beneficiary to verify.
     * @param verifyId identifies which registered beneficiary to verify.
     * @param metadata optional metadata to attach to the verification if provided.
     * @return a new verification {@link Intent}.
     */
    @Deprecated("Use SimprintsContract and SimprintsRequest.Verify instead")
    @JvmOverloads
    fun verify(
        moduleId: String,
        verifyId: String,
        metadata: Metadata? = null,
    ) = createBaseIntent(Constants.SIMPRINTS_VERIFY_INTENT)
        .putExtra(Constants.SIMPRINTS_MODULE_ID, moduleId)
        .putExtra(Constants.SIMPRINTS_VERIFY_GUID, verifyId)
        .appendOptionalMetadata(metadata)

    /**
     * Sends which GUID was confirmed in a session back to SimprintsId.
     *
     * @param sessionId    identifies the identification session.
     * @param selectedGuid the GUID that was confirmed in the host app.
     * @return a new confirm indentity {@link Intent}.
     */
    @Deprecated("Use SimprintsContract and SimprintsRequest.ConfirmIdentity instead")
    fun confirmIdentity(
        sessionId: String,
        selectedGuid: String,
    ) = createBaseIntent(Constants.SIMPRINTS_SELECT_GUID_INTENT)
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
     * @param metadata  metadata to attach to the registration if provided.
     * @return a new registration for last biometrics {@link Intent}.
     */
    @Deprecated("Use SimprintsContract and SimprintsRequest.EnrolLastBiometric instead")
    @JvmOverloads
    fun registerLastBiometrics(
        moduleId: String,
        sessionId: String,
        metadata: Metadata? = null,
    ) = createBaseIntent(Constants.SIMPRINTS_REGISTER_LAST_BIOMETRICS_INTENT)
        .putExtra(Constants.SIMPRINTS_MODULE_ID, moduleId)
        .putExtra(Constants.SIMPRINTS_SESSION_ID, sessionId)
        .appendOptionalMetadata(metadata)

    private fun createBaseIntent(action: String) = Intent(action)
        .putExtra(Constants.SIMPRINTS_PROJECT_ID, projectId)
        .putExtra(Constants.SIMPRINTS_USER_ID, userId)

    private fun Intent.appendOptionalMetadata(metadata: Metadata?) =
        if (metadata == null) this
        else putExtra(Constants.SIMPRINTS_METADATA, metadata.toString())
}
