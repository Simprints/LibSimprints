package com.simprints.libsimprints

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.simprints.libsimprints.Metadata.InvalidMetadataException
import junit.framework.TestCase.assertEquals
import org.junit.Assert.fail
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MetadataTest {
    @Test
    fun `constructs from blank string`() {
        val result = Metadata("      ")

        assertEquals("""{}""", result.toString())
    }

    @Test
    fun `constructs from valid fields`() {
        val result = Metadata(
            """
            {
                "key1": 1,
                "key2":true,
                "key3": "value",
                "key4": 0.5 
            }
            """.trimIndent(),
        )

        assertEquals("""{"key1":1,"key2":true,"key3":"value","key4":0.5}""", result.toString())
    }

    @Test
    fun `fails to construct from array fields`() {
        try {
            Metadata("""{"key1": 1,"key":[]}""")

            fail("Should have thrown")
        } catch (e: Exception) {
            assertEquals(InvalidMetadataException::class.java, e::class.java)
        }
    }

    @Test
    fun `fails to construct from object fields`() {
        try {
            Metadata("""{"key1": 1,"key":{}}""")

            fail("Should have thrown")
        } catch (e: Exception) {
            assertEquals(InvalidMetadataException::class.java, e::class.java)
        }
    }

    @Test
    fun `fails to construct from invalid string`() {
        try {
            Metadata("test")

            fail("Should have thrown")
        } catch (e: Exception) {
            assertEquals(InvalidMetadataException::class.java, e::class.java)
        }
    }

    @Test
    fun `puts objects correctly`() {
        val result = Metadata()
        result.put("key1", 1)
        result.put("key2", true)
        result.put("key3", "value")
        result.put("key4", 0.5)

        assertEquals("""{"key1":1,"key2":true,"key3":"value","key4":0.5}""", result.toString())
    }
}
