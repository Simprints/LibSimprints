package com.simprints.libsimprints.contracts.data

/**
 * This constructor creates a new scanned credential response.
 *
 * @param type The type of the credential (QR, NHIS Card, Ghana ID Card, etc).
 * @param value The value of the credential.
 */
data class ScannedCredential(
    val type: String,
    val value: String,
) {
    fun toJson(): String = asJsonObject {
        it.put(KEY_TYPE, type)
        it.put(KEY_VALUE, value)
    }.toString()

    companion object {
        private const val KEY_TYPE = "type"
        private const val KEY_VALUE = "value"

        fun fromJson(jsonString: String): ScannedCredential? = fromJsonString(jsonString) { json ->
            val type = json.getString(KEY_TYPE)
            val value = json.getString(KEY_VALUE)

            ScannedCredential(type, value)
        }
    }
}
