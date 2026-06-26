package com.simprints.libsimprints.contracts.data

import org.json.JSONArray

/**
 * This constructor creates a new identification result
 *
 * @param guid                 Global unique id of the verified person
 * @param confidence           An int containing the (matching) confidence
 * @param confidenceBand       Confidence qualifier based on the project's configuration
 * @param isLinkedToCredential GUID is linked to a credential in the system, null if external credential collection is not enabled
 * @param isCredentialVerified Linked credential has been successfully verified, null if external credential collection is not enabled
 */
data class Identification(
    val guid: String,
    val confidence: Float,
    val confidenceBand: ConfidenceBand,
    val isLinkedToCredential: Boolean? = null,
    val isCredentialVerified: Boolean? = null,
) : Comparable<Identification> {
    override fun compareTo(other: Identification): Int = when {
        confidence == other.confidence -> 0
        confidence < other.confidence -> 1
        else -> -1
    }

    companion object {
        private const val KEY_GUID = "guid"
        private const val KEY_CONFIDENCE = "confidence"
        private const val KEY_CONFIDENCE_BAND = "confidenceBand"
        private const val KEY_IS_LINKED_TO_CREDENTIAL = "isLinkedToCredential"
        private const val KEY_IS_CREDENTIAL_VERIFIED = "isVerified"

        fun List<Identification>.toJson(): String = map { id ->
            id.asJsonObject { json ->
                json.put(KEY_GUID, id.guid)
                json.put(KEY_CONFIDENCE_BAND, id.confidenceBand.name)
                json.put(KEY_CONFIDENCE, id.confidence)
                json.put(KEY_IS_LINKED_TO_CREDENTIAL, id.isLinkedToCredential)
                json.put(KEY_IS_CREDENTIAL_VERIFIED, id.isCredentialVerified)
            }
        }.let { JSONArray(it) }.toString()

        fun fromJson(jsonString: String): List<Identification>? = fromJsonArrayString(jsonString) { json ->
            val guid = json.getString(KEY_GUID)
            val confidence = json.getDouble(KEY_CONFIDENCE).toFloat()
            val band = json.getString(KEY_CONFIDENCE_BAND).let { ConfidenceBand.valueOf(it) }

            val isLinkedToCredential = json
                .takeUnless { it.isNull(KEY_IS_LINKED_TO_CREDENTIAL) }
                ?.getBoolean(KEY_IS_LINKED_TO_CREDENTIAL)

            val isCredentialVerified = json
                .takeUnless { it.isNull(KEY_IS_CREDENTIAL_VERIFIED) }
                ?.getBoolean(KEY_IS_CREDENTIAL_VERIFIED)

            Identification(guid, confidence, band, isLinkedToCredential, isCredentialVerified)
        }
    }
}
