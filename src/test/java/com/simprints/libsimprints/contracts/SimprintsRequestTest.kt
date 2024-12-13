package com.simprints.libsimprints.contracts

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.simprints.libsimprints.Constants
import com.simprints.libsimprints.Metadata
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SimprintsRequestTest {
    @Test
    fun `creates correct enrol intent`() {
        val intent = SimprintsRequest
            .Enrol(
                projectId = "project-id",
                userId = "user-id",
                moduleId = "module-id",
                metadata = Metadata().put("key", "value"),
            ).toIntent()

        assertEquals(Constants.SIMPRINTS_ENROL_INTENT, intent.action)
        assertEquals("project-id", intent.getStringExtra(Constants.SIMPRINTS_PROJECT_ID))
        assertEquals("user-id", intent.getStringExtra(Constants.SIMPRINTS_USER_ID))
        assertEquals("module-id", intent.getStringExtra(Constants.SIMPRINTS_MODULE_ID))
        assertEquals(METADATA_JSON, intent.getStringExtra(Constants.SIMPRINTS_METADATA))
    }

    @Test
    fun `creates correct identify intent`() {
        val intent = SimprintsRequest
            .Identify(
                projectId = "project-id",
                userId = "user-id",
                moduleId = "module-id",
                metadata = Metadata().put("key", "value"),
            ).toIntent()

        assertEquals(Constants.SIMPRINTS_IDENTIFY_INTENT, intent.action)
        assertEquals("project-id", intent.getStringExtra(Constants.SIMPRINTS_PROJECT_ID))
        assertEquals("user-id", intent.getStringExtra(Constants.SIMPRINTS_USER_ID))
        assertEquals("module-id", intent.getStringExtra(Constants.SIMPRINTS_MODULE_ID))
        assertEquals(METADATA_JSON, intent.getStringExtra(Constants.SIMPRINTS_METADATA))
    }

    @Test
    fun `creates correct verify intent`() {
        val intent = SimprintsRequest
            .Verify(
                projectId = "project-id",
                userId = "user-id",
                moduleId = "module-id",
                verifyId = "guid",
                metadata = Metadata().put("key", "value"),
            ).toIntent()

        assertEquals(Constants.SIMPRINTS_VERIFY_INTENT, intent.action)
        assertEquals("project-id", intent.getStringExtra(Constants.SIMPRINTS_PROJECT_ID))
        assertEquals("user-id", intent.getStringExtra(Constants.SIMPRINTS_USER_ID))
        assertEquals("module-id", intent.getStringExtra(Constants.SIMPRINTS_MODULE_ID))
        assertEquals("guid", intent.getStringExtra(Constants.SIMPRINTS_VERIFY_GUID))
        assertEquals(METADATA_JSON, intent.getStringExtra(Constants.SIMPRINTS_METADATA))
    }

    @Test
    fun `creates correct confirm intent`() {
        val intent = SimprintsRequest
            .ConfirmIdentity(
                projectId = "project-id",
                userId = "user-id",
                sessionId = "session-id",
                selectedGuid = "guid",
            ).toIntent()

        assertEquals(Constants.SIMPRINTS_CONFIRM_IDENTITY_INTENT, intent.action)
        assertEquals("project-id", intent.getStringExtra(Constants.SIMPRINTS_PROJECT_ID))
        assertEquals("user-id", intent.getStringExtra(Constants.SIMPRINTS_USER_ID))
        assertEquals("session-id", intent.getStringExtra(Constants.SIMPRINTS_SESSION_ID))
        assertEquals("guid", intent.getStringExtra(Constants.SIMPRINTS_SELECTED_GUID))
    }

    @Test
    fun `creates correct enrol last intent`() {
        val intent = SimprintsRequest
            .EnrolLastBiometrics(
                projectId = "project-id",
                userId = "user-id",
                moduleId = "module-id",
                sessionId = "session-id",
                metadata = Metadata().put("key", "value"),
            ).toIntent()

        assertEquals(Constants.SIMPRINTS_ENROL_LAST_BIOMETRICS_INTENT, intent.action)
        assertEquals("project-id", intent.getStringExtra(Constants.SIMPRINTS_PROJECT_ID))
        assertEquals("user-id", intent.getStringExtra(Constants.SIMPRINTS_USER_ID))
        assertEquals("module-id", intent.getStringExtra(Constants.SIMPRINTS_MODULE_ID))
        assertEquals("session-id", intent.getStringExtra(Constants.SIMPRINTS_SESSION_ID))
        assertEquals(METADATA_JSON, intent.getStringExtra(Constants.SIMPRINTS_METADATA))
    }

    @Test
    fun `creates correct intent without meta data`() {
        val intent = SimprintsRequest
            .Enrol(
                projectId = "project-id",
                userId = "user-id",
                moduleId = "module-id",
            ).toIntent()

        assertEquals(Constants.SIMPRINTS_ENROL_INTENT, intent.action)
        assertEquals("project-id", intent.getStringExtra(Constants.SIMPRINTS_PROJECT_ID))
        assertEquals("user-id", intent.getStringExtra(Constants.SIMPRINTS_USER_ID))
        assertEquals("module-id", intent.getStringExtra(Constants.SIMPRINTS_MODULE_ID))
        assertNull(intent.getStringExtra(Constants.SIMPRINTS_METADATA))
    }

    companion object {
        private const val METADATA_JSON = """{"key":"value"}"""
    }
}
