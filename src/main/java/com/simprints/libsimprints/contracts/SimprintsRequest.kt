package com.simprints.libsimprints.contracts

import android.content.Intent
import com.simprints.libsimprints.Constants
import com.simprints.libsimprints.Metadata

/**
 * Each of the [SimprintsRequest] subclasses represents one of the possible flows that can be started in Simprints ID.
 *
 * Three main biometric flows are:
 * * A [Enrol] call-out will collect fingerprints and link them to a new GUID.
 * * An [Identify] call-out will collect fingerprints and search through a group of people.
 * * A [Verify] call-out will collect fingerprints and verify a specific user.
 *
 * Additionally there are two possible follow-up flows:
 * * A [ConfirmIdentity] call-out will notify SID that provided GUID matches the identified person.
 * * An [EnrolLastBiometrics] call-out will perform a new registration/enrolment using the last collected biometric data.
 *
 * [More info](https://simprints.gitbook.io/docs/development/simprints-for-developers/integrating-with-simprints)
 */
sealed class SimprintsRequest {

    abstract val userId: String
    abstract val projectId: String

    abstract fun toIntent(): Intent

    /**
     * Data required to start the registration/enrolment request flow in Simprints ID.
     *
     * Response data will either contain a [Registration] object with the GUID and the templates, or additional data such as errors and refusal data.
     * If Enrolment+ is enabled, list of [Identify] results could be returned if there are any close matches.
     *
     * [More info](https://simprints.gitbook.io/docs/development/simprints-for-developers/integrating-with-simprints/enrollment)
     *
     * @param projectId identifies the project that is making the call-out. Will be checked if it matches the scanned project id upon signing-in.
     * @param userId    identifies which user is making a request to Simprints ID. Can be any arbitrary String.
     * @param moduleId  identifies which module to register into.
     * @param metadata  optional metadata to attach to the registration if provided.
     */
    data class Enrol(
        override val projectId: String,
        override val userId: String,
        val moduleId: String,
        val metadata: Metadata? = null,
    ) : SimprintsRequest() {

        override fun toIntent() = Intent(Constants.SIMPRINTS_ENROL_INTENT)
            .appendAuthFields(projectId, userId)
            .putExtra(Constants.SIMPRINTS_MODULE_ID, moduleId)
            .appendOptionalMetadata(metadata)
    }

    /**
     * Data required to start the identification request flow in Simprints ID.
     *
     * Response data will either contain an array of [Identify] objects, or additional data such as errors and refusal data.
     *
     * [More info](https://simprints.gitbook.io/docs/development/simprints-for-developers/integrating-with-simprints/identification)
     *
     * @param projectId identifies the project that is making the call-out. Will be checked if it matches the scanned project id upon signing-in.
     * @param userId    identifies which user is making a request to Simprints ID. Can be any arbitrary String.
     * @param moduleId  identifies which module to register into.
     * @param metadata  optional metadata to attach to the registration if provided.
     */
    data class Identify(
        override val projectId: String,
        override val userId: String,
        val moduleId: String,
        val metadata: Metadata? = null,
    ) : SimprintsRequest() {
        override fun toIntent() = Intent(Constants.SIMPRINTS_IDENTIFY_INTENT)
            .appendAuthFields(projectId, userId)
            .putExtra(Constants.SIMPRINTS_MODULE_ID, moduleId)
            .appendOptionalMetadata(metadata)
    }

    /**
     * Data required to start the verification request flow in Simprints ID.
     *
     * Response data will either contain a [Verify] object, or additional data such as errors and refusal data.
     *
     * [More info](https://simprints.gitbook.io/docs/development/simprints-for-developers/integrating-with-simprints/verification)
     *
     * @param projectId identifies the project that is making the call-out. Will be checked if it matches the scanned project id upon signing-in.
     * @param userId    identifies which user is making a request to Simprints ID. Can be any arbitrary String.
     * @param moduleId  identifies which module to register into.
     * @param verifyId  identifies which registered beneficiary to verify.
     * @param metadata  optional metadata to attach to the registration if provided.
     */
    data class Verify(
        override val projectId: String,
        override val userId: String,
        val moduleId: String,
        val verifyId: String,
        val metadata: Metadata? = null,
    ) : SimprintsRequest() {
        override fun toIntent() = Intent(Constants.SIMPRINTS_VERIFY_INTENT)
            .appendAuthFields(projectId, userId)
            .putExtra(Constants.SIMPRINTS_MODULE_ID, moduleId)
            .putExtra(Constants.SIMPRINTS_VERIFY_GUID, verifyId)
            .appendOptionalMetadata(metadata)
    }

    /**
     * Data required to start the identity confirmation follow-up flow in Simprints ID.
     *
     * [More info](https://simprints.gitbook.io/docs/development/simprints-for-developers/integrating-with-simprints/enrolment-and-identification-+)
     *
     * @param projectId    identifies the project that is making the call-out. Will be checked if it matches the scanned project id upon signing-in.
     * @param userId       identifies which user is making a request to Simprints ID. Can be any arbitrary String.
     * @param sessionId    identifies the identification session.
     * @param selectedGuid the GUID that was confirmed in the host app.
     */
    data class ConfirmIdentity(
        override val projectId: String,
        override val userId: String,
        val sessionId: String,
        val selectedGuid: String,
    ) : SimprintsRequest() {
        override fun toIntent() = Intent(Constants.SIMPRINTS_CONFIRM_IDENTITY_INTENT)
            .appendAuthFields(projectId, userId)
            .putExtra(Constants.SIMPRINTS_SESSION_ID, sessionId)
            .putExtra(Constants.SIMPRINTS_SELECTED_GUID, selectedGuid)
    }

    /**
     * Data required to start the last biometric enrolment follow-up flow in Simprints ID.
     *
     * Result data will either contain a [Registration] object with the GUID and the templates, or an error.
     *
     * [More info](https://simprints.gitbook.io/docs/development/simprints-for-developers/integrating-with-simprints/enrolment-and-identification-+)
     *
     * @param projectId identifies the project that is making the call-out. Will be checked if it matches the scanned project id upon signing-in.
     * @param userId    identifies which user is making a request to Simprints ID. Can be any arbitrary String.
     * @param moduleId  identifies which module to register into.
     * @param sessionId identifies the identification session.
     * @param metadata  optional metadata to attach to the registration if provided.
     */
    data class EnrolLastBiometrics(
        override val projectId: String,
        override val userId: String,
        val moduleId: String,
        val sessionId: String,
        val metadata: Metadata? = null,
    ) : SimprintsRequest() {
        override fun toIntent() = Intent(Constants.SIMPRINTS_ENROL_LAST_BIOMETRICS_INTENT)
            .appendAuthFields(projectId, userId)
            .putExtra(Constants.SIMPRINTS_MODULE_ID, moduleId)
            .putExtra(Constants.SIMPRINTS_SESSION_ID, sessionId)
            .appendOptionalMetadata(metadata)
    }

    protected fun Intent.appendAuthFields(projectId: String, userId: String) = this
        .putExtra(Constants.SIMPRINTS_PROJECT_ID, projectId)
        .putExtra(Constants.SIMPRINTS_USER_ID, userId)

    protected fun Intent.appendOptionalMetadata(metadata: Metadata?) =
        if (metadata == null) this
        else putExtra(Constants.SIMPRINTS_METADATA, metadata.toString())
}
