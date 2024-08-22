package com.simprints.libsimprints.contracts.data

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

internal inline fun <T> T.asJsonObject(
    crossinline block: (JSONObject) -> JSONObject,
): JSONObject = JSONObject().also { block(it) }

internal inline fun <T> fromJsonString(
    jsonString: String,
    crossinline block: (JSONObject) -> T,
): T? = try {
    val json = JSONObject(jsonString)
    block(json)
} catch (_: JSONException) {
    null
}

internal inline fun <T> fromJsonArrayString(
    jsonString: String,
    crossinline block: (JSONObject) -> T,
): List<T>? = try {
    val array = JSONArray(jsonString)
    val list = mutableListOf<T>()
    for (i in 0 until array.length()) {
        val json = array.getJSONObject(i)
        list.add(block(json))
    }
    list
} catch (_: JSONException) {
    null
}
