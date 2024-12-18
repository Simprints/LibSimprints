package com.simprints.libsimprints.contracts.data

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RefusalFormTest {
    @Test
    fun `test refusal parcelling`() {
        val expected = RefusalForm("reason", "extra")
        val actual = RefusalForm.fromJson(expected.toJson())

        Assert.assertEquals(expected, actual)
    }
}
