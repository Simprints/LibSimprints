package com.simprints.libsimprints.contracts

import android.content.Intent
import com.simprints.libsimprints.Constants
import com.simprints.libsimprints.Identification
import com.simprints.libsimprints.Metadata


internal fun Intent.appendAuthFields(projectId: String, userId: String) = this
    .putExtra(Constants.SIMPRINTS_PROJECT_ID, projectId)
    .putExtra(Constants.SIMPRINTS_USER_ID, userId)

internal fun Intent.appendOptionalMetadata(metadata: Metadata?) =
    if (metadata == null) this
    else putExtra(Constants.SIMPRINTS_METADATA, metadata.toString())


@Suppress("UNCHECKED_CAST")
internal fun Intent?.toResult(resultCode: Int): SimprintsResponse = when {
    resultCode != Constants.SIMPRINTS_OK -> SimprintsResponse(resultCode = resultCode)
    this == null -> SimprintsResponse(resultCode = Constants.SIMPRINTS_CANCELLED)

    hasExtra(Constants.SIMPRINTS_REFUSAL_FORM) -> SimprintsResponse(
        resultCode = resultCode,
        biometricsComplete = getBooleanExtra(Constants.SIMPRINTS_BIOMETRICS_COMPLETE_CHECK, false),
        sessionId = getStringExtra(Constants.SIMPRINTS_SESSION_ID),
        refusal = getParcelableExtra(Constants.SIMPRINTS_REFUSAL_FORM)
    )

    hasExtra(Constants.SIMPRINTS_REGISTRATION) -> SimprintsResponse(
        resultCode = resultCode,
        biometricsComplete = getBooleanExtra(Constants.SIMPRINTS_BIOMETRICS_COMPLETE_CHECK, false),
        sessionId = getStringExtra(Constants.SIMPRINTS_SESSION_ID),
        registration = getParcelableExtra(Constants.SIMPRINTS_REGISTRATION)
    )

    hasExtra(Constants.SIMPRINTS_IDENTIFICATIONS) -> SimprintsResponse(
        resultCode = resultCode,
        biometricsComplete = getBooleanExtra(Constants.SIMPRINTS_BIOMETRICS_COMPLETE_CHECK, false),
        sessionId = getStringExtra(Constants.SIMPRINTS_SESSION_ID),
        identifications = getParcelableArrayExtra(Constants.SIMPRINTS_VERIFICATION) as List<Identification>?
    )

    hasExtra(Constants.SIMPRINTS_VERIFICATION) -> SimprintsResponse(
        resultCode = resultCode,
        biometricsComplete = getBooleanExtra(Constants.SIMPRINTS_BIOMETRICS_COMPLETE_CHECK, false),
        sessionId = getStringExtra(Constants.SIMPRINTS_SESSION_ID),
        verification = getParcelableExtra(Constants.SIMPRINTS_VERIFICATION)
    )

    else -> SimprintsResponse(resultCode = Constants.SIMPRINTS_CANCELLED)
}
