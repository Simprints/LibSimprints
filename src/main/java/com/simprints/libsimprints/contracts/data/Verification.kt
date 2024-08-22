package com.simprints.libsimprints.contracts.data

/**
 * This constructor creates a new identification
 *
 * @param guid       Global unique id of the verified person
 * @param confidence An int containing the (matching) confidence
 * @param tier       The tier score derived from the confidence
 */
data class Verification(
    val guid: String,
    val confidence: Float,
    val tier: Tier,
    val isSuccess: Boolean,
) {

    fun toJson(): String = asJsonObject {
        it.put(KEY_GUID, guid)
        it.put(KEY_TIER, tier.name)
        it.put(KEY_CONFIDENCE, confidence)
        it.put(KEY_IS_SUCCESS, isSuccess)
    }.toString()

    companion object {
        private const val KEY_GUID = "guid"
        private const val KEY_TIER = "tier"
        private const val KEY_CONFIDENCE = "confidence"
        private const val KEY_IS_SUCCESS = "isSuccess"

        fun fromJson(jsonString: String): Verification? = fromJsonString(jsonString) { json ->
            val guid = json.getString(KEY_GUID)
            val tier = json.getString(KEY_TIER).let { Tier.valueOf(it) }
            val confidence = json.getDouble(KEY_CONFIDENCE).toFloat()
            val isSuccess = json.getBoolean(KEY_IS_SUCCESS)

            Verification(guid, confidence, tier, isSuccess)
        }
    }
}
