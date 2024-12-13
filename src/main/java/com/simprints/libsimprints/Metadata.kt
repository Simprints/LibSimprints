package com.simprints.libsimprints

import org.json.JSONException
import org.json.JSONObject

/**
 * A set of key/value mappings. Keys are unique, non-null strings. Values may be any mix of non null strings, booleans, longs or finite doubles.
 * <p>
 * Metadata can be attached to any registration, identification or verification action, for later statistical use.
 * <p>
 * Example: one could attach the age of the enrollee and the city they were enrolled, in order to find out the average age of beneficiaries for each city.
 */
data class Metadata(
    private var json: JSONObject = JSONObject(),
) {
    /**
     * Thrown when provided meta-data cannot be marshalled or unmarshalled from the intent extras.
     */
    class InvalidMetadataException(
        message: String,
    ) : RuntimeException(message)

    /**
     * Constructs a new Metadata object with key/value mappings from a JSON object string.
     * Only boolean, string, long and floating point values are allowed (no nested arrays or key/value mappings).
     *
     * @param jsonString a JSON-encoded string containing an object.
     * @throws InvalidMetadataException if the passed string is not a valid JSON object string,
     *                                  or it contains nested arrays or key/value mappings.
     */
    constructor(jsonString: String) : this() {
        if (jsonString.isBlank()) return

        // Parse the JSON-encoded string
        try {
            json = JSONObject(jsonString)
        } catch (e: JSONException) {
            throw InvalidMetadataException("The metadata string is not a valid JSON object string.")
        }
        // Make sure the metadata string does not contain nested arrays or key-value mappings
        val keys = json.keys()
        while (keys.hasNext()) {
            val key = keys.next()
            if (json.optJSONArray(key) != null || json.optJSONObject(key) != null) {
                throw InvalidMetadataException("Only boolean, string, integer and floating point values are allowed.")
            }
        }
    }

    /**
     * Adds a new key/boolean value mapping to this metadata object, replacing any mapping with the same key.
     *
     * @param key   a non-null string.
     * @param value a boolean.
     * @return this object.
     */
    fun put(
        key: String,
        value: Boolean,
    ) = putObject(key, value)

    /**
     * Adds a new key/double value mapping to this metadata object, replacing any mapping with the same key.
     *
     * @param key   a non-null string.
     * @param value a finite double. May not be NaNs or infinities.
     * @return this object.
     */
    fun put(
        key: String,
        value: Double,
    ) = putObject(key, value)

    /**
     * Adds a new key/long value mapping to this metadata object, replacing any mapping with the same key.
     *
     * @param key   a non-null string.
     * @param value a long.
     * @return this object.
     */
    fun put(
        key: String,
        value: Long,
    ) = putObject(key, value)

    /**
     * Adds a new key/string value mapping to this metadata object, replacing any mapping with the same key.
     *
     * @param key   a non-null string.
     * @param value a non-null string.
     * @return this object.
     */
    fun put(
        key: String,
        value: String,
    ) = putObject(key, value)

    private fun putObject(
        key: String,
        value: Any,
    ): Metadata {
        try {
            json.put(key, value)
        } catch (e: JSONException) {
            throw RuntimeException(e)
        }
        return this
    }

    /**
     * Encodes this object as a compact JSON string.
     *
     * @return a string representation of the object.
     */
    override fun toString() = json.toString()
}
