package com.simprints.libsimprints;

import android.os.Parcel;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class RefusalFormTest {

    @Test
    public void testRefusalFormParcelling() {
        RefusalForm example = new RefusalForm("No Scanner", "Forgot Scanner");

        Parcel parcel = Parcel.obtain();
        example.writeToParcel(parcel, 0);

        parcel.setDataPosition(0);

        RefusalForm createdFromParcel = RefusalForm.CREATOR.createFromParcel(parcel);
        Assert.assertEquals(example, createdFromParcel);
    }
}
