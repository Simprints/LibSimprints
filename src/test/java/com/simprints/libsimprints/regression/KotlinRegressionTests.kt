package com.simprints.libsimprints.regression

import android.content.Intent
import android.os.Bundle
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.simprints.libsimprints.Constants
import com.simprints.libsimprints.FingerIdentifier
import com.simprints.libsimprints.Identification
import com.simprints.libsimprints.Metadata
import com.simprints.libsimprints.RefusalForm
import com.simprints.libsimprints.Registration
import com.simprints.libsimprints.SimHelper
import com.simprints.libsimprints.Tier
import com.simprints.libsimprints.Verification
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.also
import kotlin.apply

/**
 * This test class represents the recommended/documented way to integrate LibSimprints within the caller app.
 * Contents of this test file MUST NOT change during any refactoring as it would mean
 * a breaking change in the API and potential issues if the calling app updates the version.
 */
@RunWith(AndroidJUnit4::class)
class KotlinRegressionTests {

    @Test
    fun enrolmentSetUp() {
        val intent = SimHelper("project-id", "user-id").register("module-id")

        assertEquals(Constants.SIMPRINTS_REGISTER_INTENT, intent.getAction())
        assertEquals(
            setOf(
                Constants.SIMPRINTS_PROJECT_ID,
                Constants.SIMPRINTS_USER_ID,
                Constants.SIMPRINTS_MODULE_ID
            ),
            intent.getExtras()?.keySet()
        )
    }

    @Test
    fun enrolmentWithMetaDataSetUp() {
        val intent = SimHelper("project-id", "user-id").register("module-id", createMetadata())

        assertEquals(Constants.SIMPRINTS_REGISTER_INTENT, intent.getAction())
        assertEquals(
            setOf(
                Constants.SIMPRINTS_PROJECT_ID,
                Constants.SIMPRINTS_USER_ID,
                Constants.SIMPRINTS_MODULE_ID,
                Constants.SIMPRINTS_METADATA
            ),
            intent.getExtras()?.keySet()
        )
        assertEquals(KEY_VALUE_JSON, intent.getStringExtra(Constants.SIMPRINTS_METADATA))
    }

    @Test
    fun identificationSetUp() {
        val intent = SimHelper("project-id", "user-id").identify("module-id")

        assertEquals(Constants.SIMPRINTS_IDENTIFY_INTENT, intent.getAction())
        assertEquals(
            setOf(
                Constants.SIMPRINTS_PROJECT_ID,
                Constants.SIMPRINTS_USER_ID,
                Constants.SIMPRINTS_MODULE_ID
            ),
            intent.getExtras()?.keySet()
        )
    }

    @Test
    fun identificationWithMetaDataSetUp() {
        val intent = SimHelper("project-id", "user-id").identify("module-id", createMetadata())

        assertEquals(Constants.SIMPRINTS_IDENTIFY_INTENT, intent.getAction())
        assertEquals(
            setOf(
                Constants.SIMPRINTS_PROJECT_ID,
                Constants.SIMPRINTS_USER_ID,
                Constants.SIMPRINTS_MODULE_ID,
                Constants.SIMPRINTS_METADATA
            ),
            intent.getExtras()?.keySet()
        )
        assertEquals(KEY_VALUE_JSON, intent.getStringExtra(Constants.SIMPRINTS_METADATA))
    }

    @Test
    fun verificationSetUp() {
        val intent = SimHelper("project-id", "user-id").verify("module-id", "guid")

        assertEquals(Constants.SIMPRINTS_VERIFY_INTENT, intent.getAction())
        assertEquals(
            setOf(
                Constants.SIMPRINTS_PROJECT_ID,
                Constants.SIMPRINTS_USER_ID,
                Constants.SIMPRINTS_MODULE_ID,
                Constants.SIMPRINTS_VERIFY_GUID,
            ),
            intent.getExtras()?.keySet()
        )
    }

    @Test
    fun verificationWithMetaDataSetUp() {
        val intent = SimHelper("project-id", "user-id")
            .verify("module-id", "guid", createMetadata())

        assertEquals(Constants.SIMPRINTS_VERIFY_INTENT, intent.getAction())
        assertEquals(
            setOf(
                Constants.SIMPRINTS_PROJECT_ID,
                Constants.SIMPRINTS_USER_ID,
                Constants.SIMPRINTS_MODULE_ID,
                Constants.SIMPRINTS_VERIFY_GUID,
                Constants.SIMPRINTS_METADATA
            ),
            intent.getExtras()?.keySet()
        )
        assertEquals(KEY_VALUE_JSON, intent.getStringExtra(Constants.SIMPRINTS_METADATA))
    }

    @Test
    fun confirmSetUp() {
        val intent = SimHelper("project-id", "user-id").confirmIdentity("module-id", "guid")

        assertEquals(Constants.SIMPRINTS_SELECT_GUID_INTENT, intent.getAction())
        assertEquals(
            setOf(
                Constants.SIMPRINTS_PROJECT_ID,
                Constants.SIMPRINTS_USER_ID,
                Constants.SIMPRINTS_SESSION_ID,
                Constants.SIMPRINTS_SELECTED_GUID
            ),
            intent.getExtras()?.keySet()
        )
    }

    @Test
    fun enrolLastSetUp() {
        val intent = SimHelper("project-id", "user-id").registerLastBiometrics("module-id", "guid")

        assertEquals(Constants.SIMPRINTS_REGISTER_LAST_BIOMETRICS_INTENT, intent.getAction())
        assertEquals(
            setOf(
                Constants.SIMPRINTS_PROJECT_ID,
                Constants.SIMPRINTS_USER_ID,
                Constants.SIMPRINTS_MODULE_ID,
                Constants.SIMPRINTS_SESSION_ID
            ),
            intent.getExtras()?.keySet()
        )
    }

    @Test
    fun enrolLastWithMetaDataSetUp() {
        val intent = SimHelper("project-id", "user-id")
            .registerLastBiometrics("module-id", "guid", createMetadata())

        assertEquals(Constants.SIMPRINTS_REGISTER_LAST_BIOMETRICS_INTENT, intent.getAction())
        assertEquals(
            setOf(
                Constants.SIMPRINTS_PROJECT_ID,
                Constants.SIMPRINTS_USER_ID,
                Constants.SIMPRINTS_MODULE_ID,
                Constants.SIMPRINTS_SESSION_ID,
                Constants.SIMPRINTS_METADATA,
            ),
            intent.getExtras()?.keySet()
        )
        assertEquals(KEY_VALUE_JSON, intent.getStringExtra(Constants.SIMPRINTS_METADATA))
    }

    @Test
    fun enrolmentResultHandling() {
        val registration = Registration("case-id")
        val intent = Intent().putExtras(Bundle().apply {
            putParcelable(Constants.SIMPRINTS_REGISTRATION, registration)
        })

        val result = intent.getParcelableExtra<Registration>(Constants.SIMPRINTS_REGISTRATION)
        assertEquals(registration, result)
    }

    @Test
    fun identificationResultHandling() {
        val identification = Identification("case-id", 12, Tier.TIER_1)
        val intent = Intent().putExtras(Bundle().apply {
            putParcelableArrayList(Constants.SIMPRINTS_IDENTIFICATIONS, arrayListOf(identification))
        })

        val result =
            intent.getParcelableArrayListExtra<Identification>(Constants.SIMPRINTS_IDENTIFICATIONS)
        assertEquals(identification, result?.get(0))
    }

    @Test
    fun verificationResultHandling() {
        val verification = Verification(42, Tier.TIER_3, "case-id")
        val intent = Intent().putExtras(Bundle().apply {
            putParcelable(Constants.SIMPRINTS_VERIFICATION, verification)
        })

        val result =
            intent.getParcelableExtra<Verification>(Constants.SIMPRINTS_VERIFICATION)
        assertEquals(verification, result)
    }

    @Test
    fun refusalResultHandling() {
        val refusalForm = RefusalForm("reason", "extra")
        val intent = Intent().putExtras(Bundle().apply {
            putParcelable(Constants.SIMPRINTS_REFUSAL_FORM, refusalForm)
        })

        val result = intent.getParcelableExtra<RefusalForm>(Constants.SIMPRINTS_REFUSAL_FORM)
        assertEquals(refusalForm, result)
    }

    private fun createMetadata(): Metadata = Metadata().also { it.put("key", "value") }

    companion object {
        private const val KEY_VALUE_JSON = """{"key":"value"}"""
    }
}
