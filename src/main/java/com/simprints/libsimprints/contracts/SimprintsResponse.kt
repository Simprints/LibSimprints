package com.simprints.libsimprints.contracts

import android.content.Intent
import com.simprints.libsimprints.Constants
import com.simprints.libsimprints.Identification
import com.simprints.libsimprints.RefusalForm
import com.simprints.libsimprints.Registration
import com.simprints.libsimprints.Verification

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

    // Request-specific data - only one field will be non-null
    val registration: Registration? = null,
    val verification: Verification? = null,
    val identifications: List<Identification>? = null,
    val refusal: RefusalForm? = null,

    // Co-sync data
    val subjectActions: String? = null,
) {

    companion object {

        @Suppress("UNCHECKED_CAST")
        @JvmStatic
        fun fromIntent(intent: Intent?, resultCode: Int): SimprintsResponse = when {
            resultCode != Constants.SIMPRINTS_OK -> SimprintsResponse(resultCode = resultCode)
            intent == null -> SimprintsResponse(resultCode = Constants.SIMPRINTS_CANCELLED)

            intent.hasExtra(Constants.SIMPRINTS_REFUSAL_FORM) -> SimprintsResponse(
                resultCode = resultCode,
                biometricsComplete = intent.getBooleanExtra(
                    Constants.SIMPRINTS_BIOMETRICS_COMPLETE_CHECK,
                    false
                ),
                sessionId = intent.getStringExtra(Constants.SIMPRINTS_SESSION_ID),
                refusal = intent.getParcelableExtra(Constants.SIMPRINTS_REFUSAL_FORM),
            )

            intent.hasExtra(Constants.SIMPRINTS_REGISTRATION) -> SimprintsResponse(
                resultCode = resultCode,
                biometricsComplete = intent.getBooleanExtra(
                    Constants.SIMPRINTS_BIOMETRICS_COMPLETE_CHECK,
                    false
                ),
                sessionId = intent.getStringExtra(Constants.SIMPRINTS_SESSION_ID),
                registration = intent.getParcelableExtra(Constants.SIMPRINTS_REGISTRATION),
                subjectActions = intent.getStringExtra(Constants.SIMPRINTS_COSYNC_SUBJECT_ACTIONS),
            )

            intent.hasExtra(Constants.SIMPRINTS_IDENTIFICATIONS) -> SimprintsResponse(
                resultCode = resultCode,
                biometricsComplete = intent.getBooleanExtra(
                    Constants.SIMPRINTS_BIOMETRICS_COMPLETE_CHECK,
                    false
                ),
                sessionId = intent.getStringExtra(Constants.SIMPRINTS_SESSION_ID),
                identifications = intent.getParcelableArrayListExtra<Identification>(Constants.SIMPRINTS_IDENTIFICATIONS) as List<Identification>?,
            )

            intent.hasExtra(Constants.SIMPRINTS_VERIFICATION) -> SimprintsResponse(
                resultCode = resultCode,
                biometricsComplete = intent.getBooleanExtra(
                    Constants.SIMPRINTS_BIOMETRICS_COMPLETE_CHECK,
                    false
                ),
                sessionId = intent.getStringExtra(Constants.SIMPRINTS_SESSION_ID),
                verification = intent.getParcelableExtra(Constants.SIMPRINTS_VERIFICATION),
            )

            else -> SimprintsResponse(resultCode = Constants.SIMPRINTS_CANCELLED)
        }
    }
}
