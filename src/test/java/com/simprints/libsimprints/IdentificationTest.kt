package com.simprints.libsimprints

import androidx.core.os.bundleOf
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class IdentificationTest {

    @Test
    fun `test identification parcelling`() {
        val expected = Identification("case-id", 99, Tier.TIER_1)
        val actual = bundleOf("test" to expected).getParcelable<Identification>("test")

        assertEquals(expected, actual)
    }

    @Test
    fun `test sorting`() {
        val topId = Identification("top-id", 99, Tier.TIER_1)
        val midId1 = Identification("first-mid-id", 51, Tier.TIER_3)
        val midId2 = Identification("second-mid-id", 51, Tier.TIER_3)
        val lowId = Identification("low-id", 2, Tier.TIER_5)

        val descendingOrder = listOf(topId, midId1, midId2, lowId)
        val sortedList = listOf(midId1, lowId, topId, midId2).sorted()

        for (i in descendingOrder.indices) {
            assertEquals(descendingOrder[i], sortedList[i])
        }
    }
}
