package com.simprints.libsimprints;

import androidx.core.os.bundleOf
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RefusalFormTest {

    @Test
    fun `test refusal form parcelling`() {
        val expected = RefusalForm("No Scanner", "Forgot Scanner")
        val actual = bundleOf("test" to expected).getParcelable<RefusalForm>("test")

        assertEquals(expected, actual)
    }
}
