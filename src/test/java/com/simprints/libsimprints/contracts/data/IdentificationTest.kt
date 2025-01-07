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
            Identification("case1", 99f, ConfidenceBand.HIGH),
            Identification("case2", 70f, ConfidenceBand.MEDIUM),
            Identification("case3", 50f, ConfidenceBand.LOW),
            Identification("case4", 30f, ConfidenceBand.NONE),
        )
        val actual = Identification.fromJson(expected.toJson())

        assertEquals(expected, actual)
    }
}
