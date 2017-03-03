package com.simprints.libsimprints;

import android.support.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

/**
 * A set of key/value mappings. Keys are unique, non-null strings. Values may be any mix of non null strings, booleans, longs or finite doubles.
 * <p>
 * Metadata can be attached to any registration, identification or verification action, for later statistical use.
 * <p>
 * Example: one could attach the age of the enrollee and the city they were enrolled, in order to find out the average age of beneficiaries for each city.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class Metadata {

    public class InvalidMetadataException extends Exception {
        /**
         * Constructs a new exception with the specified detail message.
         *
         * @param message the detail message. The detail message is saved for later retrieval by the getMessage() method.
         */
        InvalidMetadataException(String message) {
            super(message);
        }
    }

    final private JSONObject json;

    /**
     * Constructs a new Metadata object with key/value mappings from a JSON object string.
     * Only boolean, string, long and floating point values are allowed (no nested arrays or key/value mappings).
     *
     * @param jsonString a JSON-encoded string containing an object.
     * @throws InvalidMetadataException if the passed string is not a valid JSON object string,
     *                                  or it contains nested arrays or key/value mappings.
     */
    public Metadata(@NonNull String jsonString) throws InvalidMetadataException {
        // Parse the JSON-encoded string
        try {
            json = new JSONObject(jsonString);
        } catch (JSONException e) {
            throw new InvalidMetadataException("The metadata string is not a valid JSON object string.");
        }
        // Make sure the metadata string does not contain nested arrays or key-value mappings
        Iterator<String> keys = json.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            if (json.optJSONArray(key) != null || json.optJSONObject(key) != null)
                throw new InvalidMetadataException("Only boolean, string, integer and floating point values are allowed.");
        }
    }

    /**
     * Constructs a new empty Metadata object.
     */
    public Metadata() {
        json = new JSONObject();
    }

    /**
     * Adds a new key/boolean value mapping to this metadata object, replacing any mapping with the same key.
     *
     * @param key   a non-null string.
     * @param value a boolean.
     * @return this object.
     */
    @NonNull
    public Metadata put(@NonNull String key, boolean value) {
        return putObject(key, value);
    }

    /**
     * Adds a new key/double value mapping to this metadata object, replacing any mapping with the same key.
     *
     * @param key   a non-null string.
     * @param value a finite double. May not be NaNs or infinities.
     * @return this object.
     */
    @NonNull
    public Metadata put(@NonNull String key, double value) {
        return putObject(key, value);
    }

    /**
     * Adds a new key/long value mapping to this metadata object, replacing any mapping with the same key.
     *
     * @param key   a non-null string.
     * @param value a long.
     * @return this object.
     */
    @NonNull
    public Metadata put(@NonNull String key, long value) {
        return putObject(key, value);
    }

    /**
     * Adds a new key/string value mapping to this metadata object, replacing any mapping with the same key.
     *
     * @param key   a non-null string.
     * @param value a non-null string.
     * @return this object.
     */
    @NonNull
    public Metadata put(@NonNull String key, @NonNull String value) {
        return putObject(key, value);
    }

    /**
     * Encodes this object as a compact JSON string.
     *
     * @return a string representation of the object.
     */
    @NonNull
    public String toString() {
        return json.toString();
    }

    /**
     * Adds a new key/value mapping to this metadata object, replacing any mapping with the same key.
     *
     * @param key   a non-null string.
     * @param value a boolean, long, finite double, or non-null string.
     * @return this object.
     */
    @NonNull
    private Metadata putObject(@NonNull String key, @NonNull Object value) {
        try {
            json.put(key, value);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return this;
    }
}
