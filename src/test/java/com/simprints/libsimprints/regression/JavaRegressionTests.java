package com.simprints.libsimprints.regression;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.simprints.libsimprints.Constants;
import com.simprints.libsimprints.FingerIdentifier;
import com.simprints.libsimprints.Identification;
import com.simprints.libsimprints.Metadata;
import com.simprints.libsimprints.RefusalForm;
import com.simprints.libsimprints.Registration;
import com.simprints.libsimprints.SimHelper;
import com.simprints.libsimprints.Tier;
import com.simprints.libsimprints.Verification;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

/**
 * This test class represents the recommended/documented way to integrate LibSimprints within the caller app.
 * Contents of this test file MUST NOT change during any refactoring as it would mean
 * a breaking change in the API and potential issues if the calling app updates the version.
 */
@RunWith(AndroidJUnit4.class)
public class JavaRegressionTests {

    @Test
    public void enrolmentSetUp() {
        Intent intent = createSimHelper().register("module-id");

        Assert.assertEquals(Constants.SIMPRINTS_REGISTER_INTENT, intent.getAction());
        Assert.assertEquals(
            new HashSet<>(Arrays.asList(
                Constants.SIMPRINTS_PROJECT_ID,
                Constants.SIMPRINTS_USER_ID,
                Constants.SIMPRINTS_MODULE_ID
            )),
            Objects.requireNonNull(intent.getExtras()).keySet()
        );
    }

    @Test
    public void enrolmentWithMetaDataSetUp() {
        Metadata metadata = createMetadata();
        Intent intent = createSimHelper().register("module-id", metadata);

        Assert.assertEquals(Constants.SIMPRINTS_REGISTER_INTENT, intent.getAction());
        Assert.assertEquals(
            new HashSet<>(Arrays.asList(
                Constants.SIMPRINTS_PROJECT_ID,
                Constants.SIMPRINTS_USER_ID,
                Constants.SIMPRINTS_MODULE_ID,
                Constants.SIMPRINTS_METADATA
            )),
            Objects.requireNonNull(intent.getExtras()).keySet()
        );
        Assert.assertEquals(
            KEY_VALUE_JSON,
            intent.getStringExtra(Constants.SIMPRINTS_METADATA)
        );
    }

    @Test
    public void identificationSetUp() {
        Intent intent = createSimHelper().identify("module-id");

        Assert.assertEquals(Constants.SIMPRINTS_IDENTIFY_INTENT, intent.getAction());
        Assert.assertEquals(
            new HashSet<>(Arrays.asList(
                Constants.SIMPRINTS_PROJECT_ID,
                Constants.SIMPRINTS_USER_ID,
                Constants.SIMPRINTS_MODULE_ID
            )),
            Objects.requireNonNull(intent.getExtras()).keySet()
        );
    }

    @Test
    public void identificationDataSetUp() {
        Metadata metadata = createMetadata();
        Intent intent = createSimHelper().identify("module-id", metadata);

        Assert.assertEquals(Constants.SIMPRINTS_IDENTIFY_INTENT, intent.getAction());
        Assert.assertEquals(
            new HashSet<>(Arrays.asList(
                Constants.SIMPRINTS_PROJECT_ID,
                Constants.SIMPRINTS_USER_ID,
                Constants.SIMPRINTS_MODULE_ID,
                Constants.SIMPRINTS_METADATA
            )),
            Objects.requireNonNull(intent.getExtras()).keySet()
        );
        Assert.assertEquals(
            KEY_VALUE_JSON,
            intent.getStringExtra(Constants.SIMPRINTS_METADATA)
        );
    }

    @Test
    public void verificationSetUp() {
        Intent intent = createSimHelper().verify("module-id", "guid");

        Assert.assertEquals(Constants.SIMPRINTS_VERIFY_INTENT, intent.getAction());
        Assert.assertEquals(
            new HashSet<>(Arrays.asList(
                Constants.SIMPRINTS_PROJECT_ID,
                Constants.SIMPRINTS_USER_ID,
                Constants.SIMPRINTS_MODULE_ID,
                Constants.SIMPRINTS_VERIFY_GUID
            )),
            Objects.requireNonNull(intent.getExtras()).keySet()
        );
    }

    @Test
    public void verificationWithMetaDataSetUp() {
        Metadata metadata = createMetadata();
        Intent intent = createSimHelper().verify("module-id", "guid", metadata);

        Assert.assertEquals(Constants.SIMPRINTS_VERIFY_INTENT, intent.getAction());
        Assert.assertEquals(
            new HashSet<>(Arrays.asList(
                Constants.SIMPRINTS_PROJECT_ID,
                Constants.SIMPRINTS_USER_ID,
                Constants.SIMPRINTS_MODULE_ID,
                Constants.SIMPRINTS_VERIFY_GUID,
                Constants.SIMPRINTS_METADATA
            )),
            Objects.requireNonNull(intent.getExtras()).keySet()
        );
        Assert.assertEquals(
            KEY_VALUE_JSON,
            intent.getStringExtra(Constants.SIMPRINTS_METADATA)
        );
    }

    @Test
    public void confirmSetUp() {
        Intent intent = createSimHelper().confirmIdentity("module-id", "guid");

        Assert.assertEquals(Constants.SIMPRINTS_SELECT_GUID_INTENT, intent.getAction());
        Assert.assertEquals(
            new HashSet<>(Arrays.asList(
                Constants.SIMPRINTS_PROJECT_ID,
                Constants.SIMPRINTS_USER_ID,
                Constants.SIMPRINTS_SESSION_ID,
                Constants.SIMPRINTS_SELECTED_GUID
            )),
            Objects.requireNonNull(intent.getExtras()).keySet()
        );
    }

    @Test
    public void enrolLastSetUp() {
        Intent intent = createSimHelper().registerLastBiometrics("module-id", "session-id");

        Assert.assertEquals(Constants.SIMPRINTS_REGISTER_LAST_BIOMETRICS_INTENT, intent.getAction());
        Assert.assertEquals(
            new HashSet<>(Arrays.asList(
                Constants.SIMPRINTS_PROJECT_ID,
                Constants.SIMPRINTS_USER_ID,
                Constants.SIMPRINTS_MODULE_ID,
                Constants.SIMPRINTS_SESSION_ID
            )),
            Objects.requireNonNull(intent.getExtras()).keySet()
        );
    }

    @Test
    public void enrolLastWithMetaDataSetUp() {
        Metadata metadata = createMetadata();
        Intent intent = createSimHelper().registerLastBiometrics("module-id", "session-id", metadata);

        Assert.assertEquals(Constants.SIMPRINTS_REGISTER_LAST_BIOMETRICS_INTENT, intent.getAction());
        Assert.assertEquals(
            new HashSet<>(Arrays.asList(
                Constants.SIMPRINTS_PROJECT_ID,
                Constants.SIMPRINTS_USER_ID,
                Constants.SIMPRINTS_MODULE_ID,
                Constants.SIMPRINTS_SESSION_ID,
                Constants.SIMPRINTS_METADATA
            )),
            Objects.requireNonNull(intent.getExtras()).keySet()
        );
        Assert.assertEquals(
            KEY_VALUE_JSON,
            intent.getStringExtra(Constants.SIMPRINTS_METADATA)
        );
    }

    @Test
    public void enrolmentResultHandling() {
        Registration registration = new Registration("guid-id");
        registration.setTemplate(FingerIdentifier.LEFT_3RD_FINGER, new byte[]{3, 4, 5});
        registration.setTemplate(FingerIdentifier.RIGHT_3RD_FINGER, new byte[]{1, 2, 3});

        Intent intent = createIntentWithExtra(Constants.SIMPRINTS_REGISTRATION, registration);

        Registration result = intent.getParcelableExtra(Constants.SIMPRINTS_REGISTRATION);
        Assert.assertEquals(registration, result);
        Assert.assertEquals("guid-id", registration.getGuid());
    }

    @Test
    public void identificationResultHandling() {
        Identification identification = new Identification("guid-id", 12, Tier.TIER_1);
        ArrayList<Parcelable> identificationList = new ArrayList<>();
        identificationList.add(identification);
        Intent intent = createIntentWithExtra(Constants.SIMPRINTS_IDENTIFICATIONS, identificationList);

        List<Identification> result = intent.getParcelableArrayListExtra(Constants.SIMPRINTS_IDENTIFICATIONS);
        Assert.assertEquals(identification, Objects.requireNonNull(result).get(0));
        Assert.assertEquals("guid-id", identification.getGuid());
        Assert.assertEquals(12.0, identification.getConfidence(), 0.00001);
        Assert.assertEquals(Tier.TIER_1, identification.getTier());
    }

    @Test
    public void verificationResultHandling() {
        Verification verification = new Verification(42, Tier.TIER_3, "guid-id");
        Intent intent = createIntentWithExtra(Constants.SIMPRINTS_VERIFICATION, verification);

        Verification result = intent.getParcelableExtra(Constants.SIMPRINTS_VERIFICATION);
        Assert.assertEquals(verification, result);
        Assert.assertEquals("guid-id", verification.getGuid());
        Assert.assertEquals(42.0, verification.getConfidence(), 0.00001);
        Assert.assertEquals(Tier.TIER_3, verification.getTier());
    }

    @Test
    public void refusalResultHandling() {
        RefusalForm refusalForm = new RefusalForm("reason", "extra");
        Intent intent = createIntentWithExtra(Constants.SIMPRINTS_REFUSAL_FORM, refusalForm);

        RefusalForm result = intent.getParcelableExtra(Constants.SIMPRINTS_REFUSAL_FORM);
        Assert.assertEquals(refusalForm, result);
        Assert.assertEquals("reason", refusalForm.getReason());
        Assert.assertEquals("extra", refusalForm.getExtra());
    }

    private static @NonNull SimHelper createSimHelper() {
        return new SimHelper("project-id", "user-id");
    }

    public static final String KEY_VALUE_JSON = "{\"key\":\"value\"}";

    private static @NonNull Metadata createMetadata() {
        Metadata metadata = new Metadata();
        metadata.put("key", "value");
        return metadata;
    }

    private static Intent createIntentWithExtra(String key, Parcelable extras) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(key, extras);
        return new Intent().putExtras(bundle);
    }

    private static Intent createIntentWithExtra(String key, ArrayList<Parcelable> extras) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(key, extras);
        return new Intent().putExtras(bundle);
    }
}
