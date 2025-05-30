package com.simprints.libsimprints.contracts.data

/**
 * This constructor creates a new refusal form response
 *
 * @param reason Refusal reason chosen by user out of the provided options
 * @param extra  Additional information provided by the user
 */
data class RefusalForm(
    val reason: String,
    val extra: String,
) {
    fun toJson(): String = asJsonObject {
        it.put(KEY_REASON, reason)
        it.put(KEY_EXTRA, extra)
    }.toString()

    companion object {
        private const val KEY_REASON = "reason"
        private const val KEY_EXTRA = "extra"

        fun fromJson(jsonString: String): RefusalForm? = fromJsonString(jsonString) { json ->
            val reason = json.getString(KEY_REASON)
            val extra = json.getString(KEY_EXTRA)

            RefusalForm(reason, extra)
        }
    }
}
