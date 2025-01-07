package com.simprints.libsimprints.contracts.data

import org.json.JSONArray

/**
 * This constructor creates a new identification
 *
 * @param guid           Global unique id of the verified person
 * @param confidence     An int containing the (matching) confidence
 * @param confidenceBand Confidence qualifier based on the project's configuration
 */
data class Identification(
    val guid: String,
    val confidence: Float,
    val confidenceBand: ConfidenceBand,
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

        fun List<Identification>.toJson(): String = map { id ->
            id.asJsonObject { json ->
                json.put(KEY_GUID, id.guid)
                json.put(KEY_CONFIDENCE_BAND, id.confidenceBand.name)
                json.put(KEY_CONFIDENCE, id.confidence)
            }
        }.let { JSONArray(it) }.toString()

        fun fromJson(jsonString: String): List<Identification>? = fromJsonArrayString(jsonString) { json ->
            val guid = json.getString(KEY_GUID)
            val confidence = json.getDouble(KEY_CONFIDENCE).toFloat()
            val band = json.getString(KEY_CONFIDENCE_BAND).let { ConfidenceBand.valueOf(it) }
            Identification(guid, confidence, band)
        }
    }
}
