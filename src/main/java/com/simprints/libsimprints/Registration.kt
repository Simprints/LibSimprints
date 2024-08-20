package com.simprints.libsimprints;

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@SuppressWarnings("unused", "WeakerAccess")
@Parcelize
data class Registration @JvmOverloads constructor(
    val guid: String,
    private val templates: MutableMap<FingerIdentifier, ByteArray> = mutableMapOf(),
    private val faceTemplates: MutableList<ByteArray> = mutableListOf(),
) : Parcelable {

    fun setTemplate(fingerId: FingerIdentifier, fingerTemplate: ByteArray) {
        templates[fingerId] = fingerTemplate
    }

    fun getTemplate(fingerId: FingerIdentifier): ByteArray = templates[fingerId] ?: byteArrayOf()

    fun setFaceTemplate(faceTemplate: ByteArray) {
        faceTemplates.add(faceTemplate)
    }

    fun getFaceTemplates(): List<ByteArray> = faceTemplates

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + guid.hashCode()
        result = 31 * result + templates.hashCode()
        result = 31 * result + faceTemplates.hashCode()
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (other === this) return true
        if (other !is Registration) return false
        if (guid != other.guid) return false
        for (fingerId in FingerIdentifier.entries) {
            if (templates[fingerId]?.contentEquals(other.templates[fingerId]) == false) return false
        }
        for (i in faceTemplates.indices) {
            if (faceTemplates[i].contentEquals(other.faceTemplates.getOrNull(i)) == false) return false
        }
        return true;
    }
}
