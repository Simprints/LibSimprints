package com.simprints.libsimprints

import androidx.core.os.bundleOf
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RegistrationTest {

    @Test
    fun `test registration parcelling`() {
        val expected = Registration("case-id").apply {
            setTemplate(FingerIdentifier.LEFT_THUMB, byteArrayOf(1, 2, 3, 4))
            setTemplate(FingerIdentifier.LEFT_INDEX_FINGER, byteArrayOf(3, 2, 1))
            setTemplate(FingerIdentifier.LEFT_3RD_FINGER, byteArrayOf(7, 8, 1, 9))
            setTemplate(FingerIdentifier.LEFT_4TH_FINGER, byteArrayOf(7, 7, 7, 8, 1, 9))
            setTemplate(FingerIdentifier.LEFT_5TH_FINGER, byteArrayOf(1, 2))
            setTemplate(FingerIdentifier.RIGHT_5TH_FINGER, byteArrayOf(8, 3, 5, 3, 7, 3))
            setTemplate(FingerIdentifier.RIGHT_4TH_FINGER, byteArrayOf(7, 1, 3, 7, 0, 5))
            setTemplate(FingerIdentifier.RIGHT_3RD_FINGER, byteArrayOf(7))
            setTemplate(FingerIdentifier.RIGHT_INDEX_FINGER, byteArrayOf(1, 0))
            setTemplate(FingerIdentifier.RIGHT_THUMB, byteArrayOf(0))
            setFaceTemplate(byteArrayOf(7, 1, 3, 7, 0, 5))
        }
        val actual = bundleOf("test" to expected).getParcelable<Registration>("test")

        assertEquals(expected, actual)
    }

    @Test
    fun `test template fetching`() {
        val expected = Registration("case-id").apply {
            setTemplate(FingerIdentifier.LEFT_THUMB, byteArrayOf(1, 2, 3))
            setTemplate(FingerIdentifier.RIGHT_THUMB, byteArrayOf(0))
            setFaceTemplate(byteArrayOf(7, 1, 3, 7, 0, 5))
        }

        assertEquals(2, expected.getTemplates().size)
        assertEquals(1, expected.getFaceTemplates().size)

        assertFalse(expected.getTemplate(FingerIdentifier.LEFT_THUMB).isEmpty())
        assertTrue(expected.getTemplate(FingerIdentifier.RIGHT_5TH_FINGER).isEmpty())
    }
}