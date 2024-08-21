package com.simprints.libsimprints.contracts.data

data class Enrolment(
    val guid: String,
) {

    fun toJson(): String = asJsonObject {
        it.put(KEY_GUID, guid)
    }.toString()

    companion object {
        private const val KEY_GUID = "guid"

        fun fromJson(jsonString: String): Enrolment? = fromJsonString(jsonString) { json ->
            val guid = json.getString(KEY_GUID)

            Enrolment(guid)
        }
    }
}
