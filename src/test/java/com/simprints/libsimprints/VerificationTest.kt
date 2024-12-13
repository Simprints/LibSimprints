package com.simprints.libsimprints

import androidx.core.os.bundleOf
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class VerificationTest {
    @Test
    fun `test verification parcelling`() {
        val expected = Verification(42, Tier.TIER_2, "case-id")
        val actual = bundleOf("test" to expected).getParcelable<Verification>("test")

        assertEquals(expected, actual)
    }

    @Test
    fun `test verification confidence`() {
        val verification = Verification(42, Tier.TIER_2, "case-id")

        assertEquals(42.0f, verification.getConfidence())
    }
}
