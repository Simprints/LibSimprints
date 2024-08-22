package com.simprints.libsimprints

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * This constructor creates a new identification
 *
 * @param guid       Global unique id of the verified person
 * @param confidence An int containing the (matching) confidence
 * @param tier       The tier score derived from the confidence
 */
@Parcelize
data class Identification(
    val guid: String,
    private val confidence: Int,
    val tier: Tier,
) : Parcelable, Comparable<Identification> {

    fun getConfidence(): Float = confidence.toFloat()

    override fun compareTo(other: Identification): Int = when {
        confidence == other.confidence -> 0
        confidence < other.confidence -> 1
        else -> -1
    }
}
