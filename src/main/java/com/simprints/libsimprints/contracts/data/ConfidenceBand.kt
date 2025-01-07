package com.simprints.libsimprints.contracts.data

/**
 * Qualifies how good is the specific confidence score based
 * on the decision policy in the project configuration.
 *
 * HIGH -   highly likely (near 100% confidence) to be a match
 * MEDIUM - possible match but not 100% likely a match
 * LOW -    not a match
 * NONE -   fallback value when matching was not possible
 */
enum class ConfidenceBand {
    HIGH,
    MEDIUM,
    LOW,
    NONE,
}
