package com.simprints.libsimprints;

import android.os.Parcel;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class RegistrationTest {
    @Test
    public void testRegistrationParcelling() {

        final byte[] leftThumb = {1, 2, 3, 4};
        final byte[] leftIndex = {3, 2, 1};
        final byte[] left3rd = {7, 8, 1, 9};
        final byte[] left4th = {7, 7, 7, 7, 7, 8, 1, 9};
        final byte[] left5th = {1, 2};
        final byte[] right5th = {8, 3, 5, 3, 3, 7, 7, 3};
        final byte[] right4th = {7, 1, 3, 7, 0, 5};
        final byte[] right3rd = {7};
        final byte[] rightThumb = {1, 0};
        final byte[] rightIndex = {0};


        Registration exampleRegistration =
                new Registration("case-id");
        exampleRegistration.setTemplate(FingerIdentifier.LEFT_THUMB, leftThumb);
        exampleRegistration.setTemplate(FingerIdentifier.LEFT_INDEX_FINGER, leftIndex);
        exampleRegistration.setTemplate(FingerIdentifier.LEFT_3RD_FINGER, left3rd);
        exampleRegistration.setTemplate(FingerIdentifier.LEFT_4TH_FINGER, left4th);
        exampleRegistration.setTemplate(FingerIdentifier.LEFT_5TH_FINGER, left5th);
        exampleRegistration.setTemplate(FingerIdentifier.RIGHT_5TH_FINGER, right5th);
        exampleRegistration.setTemplate(FingerIdentifier.RIGHT_4TH_FINGER, right4th);
        exampleRegistration.setTemplate(FingerIdentifier.RIGHT_3RD_FINGER, right3rd);
        exampleRegistration.setTemplate(FingerIdentifier.RIGHT_INDEX_FINGER, rightIndex);
        exampleRegistration.setTemplate(FingerIdentifier.RIGHT_THUMB, rightThumb);

        Parcel parcel = Parcel.obtain();
        exampleRegistration.writeToParcel(parcel, 0);

        parcel.setDataPosition(0);

        Registration createdFromParcel = Registration.CREATOR.createFromParcel(parcel);
        Assert.assertEquals(exampleRegistration, createdFromParcel);
    }
}