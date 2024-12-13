package com.simprints.libsimprints.contracts

import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract

/**
 * An [ActivityResultContract] that encapsulates the interaction with the Simprints ID.
 *
 * This contract handles the creation of an [Intent] from a [SimprintsRequest] to launch a Simprints operation,
 * and the parsing of the result from the launched activity into a [SimprintsResponse].
 *
 *
 * Create an instance of [SimprintsContract] and register it with an Activity or Fragment
 * ```
 * val simprintsLauncher = registerForActivityResult(SimprintsContract()) { result ->
 *     // Handle the SimprintsResponse here
 * }
 * ```
 *
 * Call [launch] with a corresponding [SimprintsRequest] subclass to launch the corresponding Simprints ID flow.
 * ```
 * // To launch the registration/enrollment flow in Simprints ID
 * simprintsLauncher.launch(SimprintsRequest.Registration(projectId, userId, moduleId))
 * ```
 *
 * @see [registerForActivityResult]
 */
class SimprintsContract : ActivityResultContract<SimprintsRequest, SimprintsResponse>() {
    override fun createIntent(
        context: Context,
        input: SimprintsRequest,
    ): Intent = input.toIntent()

    override fun parseResult(
        resultCode: Int,
        intent: Intent?,
    ): SimprintsResponse = SimprintsResponse.fromIntent(intent, resultCode)
}
