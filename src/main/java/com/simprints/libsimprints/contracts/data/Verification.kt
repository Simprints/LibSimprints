package com.simprints.libsimprints.contracts.data

/**
 * This constructor creates a new identification
 *
 * @param guid       Global unique id of the verified person
 * @param confidence An int containing the (matching) confidence
 * @param confidenceBand Confidence qualifier based on the project's configuration
 */
data class Verification(
    val guid: String,
    val confidence: Float,
    val confidenceBand: ConfidenceBand,
    val isSuccess: Boolean,
) {
    fun toJson(): String = asJsonObject {
        it.put(KEY_GUID, guid)
        it.put(KEY_CONFIDENCE_BAND, confidenceBand.name)
        it.put(KEY_CONFIDENCE, confidence)
        it.put(KEY_IS_SUCCESS, isSuccess)
    }.toString()

    companion object {
        private const val KEY_GUID = "guid"
        private const val KEY_CONFIDENCE = "confidence"
        private const val KEY_CONFIDENCE_BAND = "confidenceBand"
        private const val KEY_IS_SUCCESS = "isSuccess"

        fun fromJson(jsonString: String): Verification? = fromJsonString(jsonString) { json ->
            val guid = json.getString(KEY_GUID)
            val confidence = json.getDouble(KEY_CONFIDENCE).toFloat()
            val band = json.getString(KEY_CONFIDENCE_BAND).let { ConfidenceBand.valueOf(it) }
            val isSuccess = json.getBoolean(KEY_IS_SUCCESS)

            Verification(guid, confidence, band, isSuccess)
        }
    }
}
