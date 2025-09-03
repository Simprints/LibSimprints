package com.simprints.libsimprints.contracts

import android.content.Intent
import com.simprints.libsimprints.Constants
import com.simprints.libsimprints.contracts.data.Enrolment
import com.simprints.libsimprints.contracts.data.Identification
import com.simprints.libsimprints.contracts.data.RefusalForm
import com.simprints.libsimprints.contracts.data.Verification
import kotlin.let

/**
 * Container class for all possible response data that Simprints ID can return.
 *
 * In all cases at most one of
 * * registration: [Registration] - in case of enrolment
 * * verification: [Verification] - in case of verification
 * * identifications: List<[Identification]> - in case of identification
 * * refusal: [RefusalForm] - in case of patient refusal
 * will be non-null if the result code is [Constants.SIMPRINTS_OK].
 *
 */
data class SimprintsResponse(
    // Data common to all requests
    val resultCode: Int,
    val biometricsComplete: Boolean = false,
    val sessionId: String? = null,
    val deviceId: String? = null,
    val appVersionName: String? = null,
    // Request-specific data - only one field will be non-null
    val enrolment: Enrolment? = null,
    val verification: Verification? = null,
    val identifications: List<Identification>? = null,
    val refusal: RefusalForm? = null,
    // Co-sync data
    val subjectActions: String? = null,
) {
    companion object {
        @Suppress("UNCHECKED_CAST")
        @JvmStatic
        fun fromIntent(
            intent: Intent?,
            resultCode: Int,
        ): SimprintsResponse {
            if (resultCode != Constants.SIMPRINTS_OK) {
                return SimprintsResponse(resultCode = resultCode)
            }
            if (intent == null) {
                return SimprintsResponse(resultCode = Constants.SIMPRINTS_CANCELLED)
            }

            return SimprintsResponse(
                resultCode = resultCode,
                biometricsComplete = intent.getBooleanExtra(
                    Constants.SIMPRINTS_BIOMETRICS_COMPLETE_CHECK,
                    false,
                ),
                sessionId = intent.getStringExtra(Constants.SIMPRINTS_SESSION_ID),
                deviceId = intent.getStringExtra(Constants.SIMPRINTS_DEVICE_ID),
                appVersionName = intent.getStringExtra(Constants.SIMPRINTS_APP_VERSION_NAME),
                refusal = intent
                    .getStringExtra(Constants.SIMPRINTS_REFUSAL_FORM)
                    ?.let { RefusalForm.fromJson(it) },
                enrolment = intent
                    .getStringExtra(Constants.SIMPRINTS_ENROLMENT)
                    ?.let { Enrolment.fromJson(it) },
                identifications = intent
                    .getStringExtra(Constants.SIMPRINTS_IDENTIFICATIONS)
                    ?.let { Identification.fromJson(it) },
                verification = intent
                    .getStringExtra(Constants.SIMPRINTS_VERIFICATION)
                    ?.let { Verification.fromJson(it) },
                subjectActions = intent.getStringExtra(Constants.SIMPRINTS_COSYNC_SUBJECT_ACTIONS),
            )
        }
    }
}
