package com.simprints.libsimprints.contracts

import android.content.Intent
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.simprints.libsimprints.Constants
import com.simprints.libsimprints.Identification
import com.simprints.libsimprints.RefusalForm
import com.simprints.libsimprints.Registration
import com.simprints.libsimprints.Tier
import com.simprints.libsimprints.Verification
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
        val result = intent.toResult(Constants.SIMPRINTS_OK)

        assertEquals(Constants.SIMPRINTS_CANCELLED, result.resultCode)
    }

    @Test
    fun `correctly parses empty intent`() {
        val intent: Intent? = null
        val result = intent.toResult(Constants.SIMPRINTS_OK)

        assertEquals(Constants.SIMPRINTS_CANCELLED, result.resultCode)
    }

    @Test
    fun `correctly parses error result intent`() {
        val result = Intent()
            // This should be ignored in final data
            .putExtra(Constants.SIMPRINTS_REFUSAL_FORM, RefusalForm("reason", "action"))
            .toResult(Constants.SIMPRINTS_UNEXPECTED_ERROR)

        assertEquals(Constants.SIMPRINTS_UNEXPECTED_ERROR, result.resultCode)
        assertNull(result.refusal)
        assertNull(result.registration)
        assertNull(result.identifications)
        assertNull(result.verification)
    }

    @Test
    fun `correctly parses refusal intent`() {
        val result = Intent()
            .putExtra(Constants.SIMPRINTS_REFUSAL_FORM, RefusalForm("reason", "action"))
            .toResult(Constants.SIMPRINTS_OK)

        assertEquals(Constants.SIMPRINTS_OK, result.resultCode)
        assertNotNull(result.refusal)
        assertNull(result.registration)
        assertNull(result.identifications)
        assertNull(result.verification)
    }

    @Test
    fun `correctly parses enrolment intent`() {
        val result = Intent()
            .putExtra(Constants.SIMPRINTS_REGISTRATION, Registration("guid"))
            .toResult(Constants.SIMPRINTS_OK)

        assertEquals(Constants.SIMPRINTS_OK, result.resultCode)
        assertNull(result.refusal)
        assertNotNull(result.registration)
        assertNull(result.identifications)
        assertNull(result.verification)
    }

    @Test
    fun `correctly parses identification intent`() {
        val result = Intent()
            .putExtra(
                Constants.SIMPRINTS_IDENTIFICATIONS,
                arrayListOf(Identification("guid", 42, Tier.TIER_1))
            )
            .toResult(Constants.SIMPRINTS_OK)

        assertEquals(Constants.SIMPRINTS_OK, result.resultCode)
        assertNull(result.refusal)
        assertNull(result.registration)
        assertNotNull(result.identifications)
        assertNull(result.verification)
    }

    @Test
    fun `correctly parses verification intent`() {
        val result = Intent()
            .putExtra(Constants.SIMPRINTS_VERIFICATION, Verification(42, Tier.TIER_1, "guid"))
            .toResult(Constants.SIMPRINTS_OK)

        assertEquals(Constants.SIMPRINTS_OK, result.resultCode)
        assertNull(result.refusal)
        assertNull(result.registration)
        assertNull(result.identifications)
        assertNotNull(result.verification)
    }
    
}
