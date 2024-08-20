package com.simprints.libsimprints.contracts

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
)
