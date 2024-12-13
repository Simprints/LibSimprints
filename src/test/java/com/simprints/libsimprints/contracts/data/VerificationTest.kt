package com.simprints.libsimprints.contracts.data

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class VerificationTest {
    @Test
    fun `test verification parcelling`() {
        val expected = Verification("case-id", 42f, Tier.TIER_4, false)
        val actual = Verification.fromJson(expected.toJson())

        Assert.assertEquals(expected, actual)
    }
}
