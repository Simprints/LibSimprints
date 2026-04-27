package com.simprints.libsimprints.contracts.data

import androidx.test.ext.junit.runners.*
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ScannedCredentialTest {
    @Test
    fun `test scanned credential parcelling`() {
        val expected = ScannedCredential("QRCode", "123456")
        val actual = ScannedCredential.fromJson(expected.toJson())

        Assert.assertEquals(expected, actual)
    }
}
