package com.simprints.libsimprints.contracts.data;

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class EnrolmentTest {

    @Test
    fun `test enrolment parcelling`() {
        val expected = Enrolment("case-id")
        val actual = Enrolment.fromJson(expected.toJson())

        assertEquals(expected, actual)
    }
}
