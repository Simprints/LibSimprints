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
@Parcelize
data class Verification @JvmOverloads constructor(
    private val confidence: Int,
    val tier: Tier,
    val guid: String,
    // TODO change to val once it is correctly returned from SID
    var isSuccess: Boolean = false,
) : Parcelable {

    fun getConfidence(): Float = confidence.toFloat()
}
