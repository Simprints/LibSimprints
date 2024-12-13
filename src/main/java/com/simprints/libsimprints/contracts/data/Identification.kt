package com.simprints.libsimprints.contracts.data

import org.json.JSONArray

/**
 * This constructor creates a new identification
 *
 * @param guid       Global unique id of the verified person
 * @param confidence An int containing the (matching) confidence
 * @param tier       The tier score derived from the confidence
 */
data class Identification(
    val guid: String,
    val confidence: Float,
    val tier: Tier,
) : Comparable<Identification> {
    override fun compareTo(other: Identification): Int = when {
        confidence == other.confidence -> 0
        confidence < other.confidence -> 1
        else -> -1
    }

    companion object {
        private const val KEY_GUID = "guid"
        private const val KEY_TIER = "tier"
        private const val KEY_CONFIDENCE = "confidence"

        fun List<Identification>.toJson(): String = map { id ->
            id.asJsonObject { json ->
                json.put(KEY_GUID, id.guid)
                json.put(KEY_TIER, id.tier.name)
                json.put(KEY_CONFIDENCE, id.confidence)
            }
        }.let { JSONArray(it) }.toString()

        fun fromJson(jsonString: String): List<Identification>? = fromJsonArrayString(jsonString) { json ->
            val guid = json.getString(KEY_GUID)
            val tier = json.getString(KEY_TIER).let { Tier.valueOf(it) }
            val confidence = json.getDouble(KEY_CONFIDENCE).toFloat()
            Identification(guid, confidence, tier)
        }
    }
}
