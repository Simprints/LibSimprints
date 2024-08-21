package com.simprints.libsimprints

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator

@SuppressWarnings("unused", "WeakerAccess")
@Deprecated("Use contracts.data.Verification instead")
data class Verification @JvmOverloads constructor(
    private val confidence: Int,
    val tier: Tier,
    val guid: String,
) : Parcelable {

    fun getConfidence(): Float = confidence.toFloat()

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(this.confidence)
        dest.writeInt(this.tier.ordinal)
        dest.writeString(this.guid)
    }

    companion object {
        @JvmField
        val CREATOR = object : Creator<Verification> {

            override fun createFromParcel(parcel: Parcel): Verification? {
                val confidence = parcel.readInt()
                val tier = Tier.entries[parcel.readInt()]
                val guid = parcel.readString().orEmpty()

                return Verification(confidence, tier, guid)
            }

            override fun newArray(p0: Int): Array<out Verification?> = arrayOfNulls(p0)
        }
    }
}
