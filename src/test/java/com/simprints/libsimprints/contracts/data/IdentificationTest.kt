package com.simprints.libsimprints.contracts.data

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.simprints.libsimprints.contracts.data.Identification.Companion.toJson
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class IdentificationTest {
    @Test
    fun `test enrolment parcelling`() {
        val expected = listOf(
            Identification("case1", 99f, Tier.TIER_1),
            Identification("case2", 70f, Tier.TIER_2),
            Identification("case3", 50f, Tier.TIER_3),
            Identification("case4", 30f, Tier.TIER_4),
            Identification("case5", 10f, Tier.TIER_5),
        )
        val actual = Identification.fromJson(expected.toJson())

        assertEquals(expected, actual)
    }
}
