package com.simprints.libsimprints

import androidx.core.os.bundleOf
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RegistrationTest {

    @Test
    fun `test registration parcelling`() {
        val expected = Registration("case-id")
        val actual = bundleOf("test" to expected).getParcelable<Registration>("test")

        assertEquals(expected, actual)
    }
}
