package com.simprints.libsimprints.contracts

import android.content.Intent
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.simprints.libsimprints.Constants
import com.simprints.libsimprints.contracts.data.Enrolment
import com.simprints.libsimprints.contracts.data.Identification
import com.simprints.libsimprints.contracts.data.Identification.Companion.toJson
import com.simprints.libsimprints.contracts.data.RefusalForm
import com.simprints.libsimprints.contracts.data.Tier
import com.simprints.libsimprints.contracts.data.Verification
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SimprintsResponseTest {
    @Test
    fun `correctly parses null intent`() {
        val intent: Intent? = null
        val result = SimprintsResponse.fromIntent(intent, Constants.SIMPRINTS_OK)

        assertEquals(Constants.SIMPRINTS_CANCELLED, result.resultCode)
    }

    @Test
    fun `correctly parses empty intent`() {
        val intent: Intent? = null
        val result = SimprintsResponse.fromIntent(intent, Constants.SIMPRINTS_OK)

        assertEquals(Constants.SIMPRINTS_CANCELLED, result.resultCode)
    }

    @Test
    fun `correctly parses error result intent`() {
        val intent = Intent().putExtra(
            // This should be ignored in final data
            Constants.SIMPRINTS_REFUSAL_FORM,
            RefusalForm("reason", "action").toJson(),
        )
        val result = SimprintsResponse.fromIntent(intent, Constants.SIMPRINTS_UNEXPECTED_ERROR)

        assertEquals(Constants.SIMPRINTS_UNEXPECTED_ERROR, result.resultCode)
        assertNull(result.refusal)
        assertNull(result.enrolment)
        assertNull(result.identifications)
        assertNull(result.verification)
    }

    @Test
    fun `correctly parses refusal intent`() {
        val intent = Intent().putExtra(
            Constants.SIMPRINTS_REFUSAL_FORM,
            RefusalForm("reason", "action").toJson(),
        )
        val result = SimprintsResponse.fromIntent(intent, Constants.SIMPRINTS_OK)

        assertEquals(Constants.SIMPRINTS_OK, result.resultCode)
        assertNotNull(result.refusal)
        assertNull(result.enrolment)
        assertNull(result.identifications)
        assertNull(result.verification)
    }

    @Test
    fun `correctly parses enrolment intent`() {
        val intent = Intent().putExtra(
            Constants.SIMPRINTS_ENROLMENT,
            Enrolment("guid").toJson(),
        )
        val result = SimprintsResponse.fromIntent(intent, Constants.SIMPRINTS_OK)

        assertEquals(Constants.SIMPRINTS_OK, result.resultCode)
        assertNull(result.refusal)
        assertNotNull(result.enrolment)
        assertNull(result.identifications)
        assertNull(result.verification)
    }

    @Test
    fun `correctly parses identification intent`() {
        val intent = Intent().putExtra(
            Constants.SIMPRINTS_IDENTIFICATIONS,
            listOf(Identification("guid", 42f, Tier.TIER_1)).toJson(),
        )
        val result = SimprintsResponse.fromIntent(intent, Constants.SIMPRINTS_OK)

        assertEquals(Constants.SIMPRINTS_OK, result.resultCode)
        assertNull(result.refusal)
        assertNull(result.enrolment)
        assertNotNull(result.identifications)
        assertNull(result.verification)
    }

    @Test
    fun `correctly parses verification intent`() {
        val intent = Intent().putExtra(
            Constants.SIMPRINTS_VERIFICATION,
            Verification("guid", 42f, Tier.TIER_1, true).toJson(),
        )
        val result = SimprintsResponse.fromIntent(intent, Constants.SIMPRINTS_OK)

        assertEquals(Constants.SIMPRINTS_OK, result.resultCode)
        assertNull(result.refusal)
        assertNull(result.enrolment)
        assertNull(result.identifications)
        assertNotNull(result.verification)
    }
}
