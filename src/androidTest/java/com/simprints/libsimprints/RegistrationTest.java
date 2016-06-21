package com.simprints.libsimprints;

import android.os.Parcel;
import android.support.test.runner.AndroidJUnit4;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class RegistrationTest {
    @Test
    public void testRegistrationParcelling() {
        final byte[] rightIndex = {0};
        final byte[] rightThumb = {1, 0};
        final byte[] leftIndex = {3, 2, 1};
        final byte[] leftThumb = {1, 2, 3, 4};
        Registration exampleRegistration =
                new Registration("case-id", rightIndex, rightThumb, leftIndex, leftThumb);

        Parcel parcel = Parcel.obtain();
        exampleRegistration.writeToParcel(parcel, 0);

        parcel.setDataPosition(0);

        Registration createdFromParcel = Registration.CREATOR.createFromParcel(parcel);
        Assert.assertEquals(exampleRegistration, createdFromParcel);
    }
}