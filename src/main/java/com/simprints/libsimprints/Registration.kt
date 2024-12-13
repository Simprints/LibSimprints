package com.simprints.libsimprints

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator

@Deprecated("Use contracts.data.Enrolment instead")
data class Registration @JvmOverloads constructor(
    val guid: String,
    private val templates: MutableMap<FingerIdentifier, ByteArray> = mutableMapOf(),
) : Parcelable {
    fun setTemplate(
        fingerId: FingerIdentifier,
        fingerTemplate: ByteArray,
    ) {
        templates[fingerId] = fingerTemplate
    }

    fun getTemplate(fingerId: FingerIdentifier): ByteArray = templates[fingerId] ?: byteArrayOf()

    override fun describeContents(): Int = 0

    override fun writeToParcel(
        dest: Parcel,
        flags: Int,
    ) {
        dest.writeString(guid)
        dest.writeInt(templates.size)

        for ((fingerId, fingerTemplate) in templates) {
            dest.writeInt(fingerId.ordinal)
            dest.writeInt(fingerTemplate.size)
            dest.writeByteArray(fingerTemplate)
        }
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + guid.hashCode()
        result = 31 * result + templates.hashCode()
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (other === this) return true
        if (other !is Registration) return false
        if (guid != other.guid) return false
        for (fingerId in FingerIdentifier.entries) {
            if (!templates[fingerId].contentEquals(other.templates[fingerId])) return false
        }
        return true
    }

    companion object {
        @JvmField
        val CREATOR = object : Creator<Registration> {
            override fun createFromParcel(parcel: Parcel): Registration? {
                val guid = parcel.readString().orEmpty()
                val nbOfTemplates = parcel.readInt()

                val templates = mutableMapOf<FingerIdentifier, ByteArray>()
                for (i in 0 until nbOfTemplates) {
                    val fingerId = FingerIdentifier.entries[parcel.readInt()]
                    val fingerTemplate = ByteArray(parcel.readInt())
                    parcel.readByteArray(fingerTemplate)
                    templates[fingerId] = fingerTemplate
                }
                return Registration(guid, templates)
            }

            override fun newArray(p0: Int): Array<out Registration?> = arrayOfNulls(p0)
        }
    }
}
