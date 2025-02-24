package com.simprints.libsimprints.contracts.data

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class VerificationTest {
    @Test
    fun `test verification parcelling`() {
        val expected = Verification("case-id", 42f, ConfidenceBand.HIGH, false)
        val actual = Verification.fromJson(expected.toJson())

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `test verification does not have isSuccess if null`() {
        val expected = Verification("case-id", 42f, ConfidenceBand.HIGH, null)
        val actual = Verification.fromJson(expected.toJson())

        Assert.assertEquals(expected, actual)
    }
}
