package com.simprints.libsimprints

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * This constructor creates a new verification
 *
 * @param confidence An int containing the (matching) confidence
 * @param tier       The tier score derived from the confidence
 * @param guid       Global unique id of the verified person
 */
@SuppressWarnings("unused", "WeakerAccess")
@Parcelize
data class Verification(
    private val confidence: Int,
    val tier: Tier,
    val guid: String,
) : Parcelable {

    fun getConfidence(): Float = confidence.toFloat()
}