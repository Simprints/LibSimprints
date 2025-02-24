package com.simprints.libsimprints

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator

@Deprecated("Use contracts.data.Identification instead")
data class Identification(
    val guid: String,
    private val confidence: Int,
    val tier: Tier,
) : Parcelable,
    Comparable<Identification> {
    fun getConfidence(): Float = confidence.toFloat()

    override fun compareTo(other: Identification): Int = when {
        confidence == other.confidence -> 0
        confidence < other.confidence -> 1
        else -> -1
    }

    override fun describeContents(): Int = 0

    override fun writeToParcel(
        dest: Parcel,
        flags: Int,
    ) {
        dest.writeString(guid)
        dest.writeInt(this.confidence)
        dest.writeInt(this.tier.ordinal)
    }

    companion object {
        @JvmField
        val CREATOR = object : Creator<Identification> {
            override fun createFromParcel(parcel: Parcel): Identification? {
                val guid = parcel.readString().orEmpty()
                val confidence = parcel.readInt()
                val tier = Tier.entries[parcel.readInt()]

                return Identification(guid, confidence, tier)
            }

            override fun newArray(p0: Int): Array<out Identification?> = arrayOfNulls(p0)
        }
    }
}
