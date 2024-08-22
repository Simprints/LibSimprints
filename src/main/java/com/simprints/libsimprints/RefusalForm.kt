package com.simprints.libsimprints;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator

@Deprecated("Use contracts.data.RefusalForm instead")
data class RefusalForm(
    val reason: String,
    val extra: String,
) : Parcelable {

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(reason)
        dest.writeString(extra)
    }

    companion object {
        @JvmField
        val CREATOR = object : Creator<RefusalForm> {

            override fun createFromParcel(parcel: Parcel): RefusalForm? {
                val reason = parcel.readString().orEmpty()
                val extra = parcel.readString().orEmpty()

                return RefusalForm(reason, extra)
            }

            override fun newArray(p0: Int): Array<out RefusalForm?> {
                return arrayOfNulls(p0)
            }
        }
    }
}
