package com.simprints.libsimprints.contracts

/**
 * List of versions in which specific changes to external API were introduced.
 *
 * These constant are mainly for Simprints ID to determine the optimal
 * response data format based on the calling app integration version.
 */
object VersionsList {
    /**
     * In this version Activity contracts were introduced with a new set of returned data classes.
     * The new API uses JSON as marshalling format instead of parcelable to make
     * adding and removing data fields simpler.
     */
    const val INITIAL_REWORK = 20250102

    /**
     * In this version deviceId and app version name fields were added to the response data
     */
    const val ADDED_DEVICE_FIELDS = 20250200
}
