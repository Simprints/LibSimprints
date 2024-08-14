package com.simprints.libsimprints;

import android.os.Parcel;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


@RunWith(AndroidJUnit4.class)
public class IdentificationTest {
    @Test
    public void testIdentificationParcelling() {
        Identification exampleId = new Identification("case-id", 99, Tier.TIER_1);

        Parcel parcel = Parcel.obtain();
        exampleId.writeToParcel(parcel, 0);

        parcel.setDataPosition(0);

        Identification createdFromParcel = Identification.CREATOR.createFromParcel(parcel);
        Assert.assertEquals(exampleId, createdFromParcel);
    }

    @Test
    public void testSorting() {
        ArrayList<Identification> idList;
        Identification topId = new Identification("top-id", 99, Tier.TIER_1);
        Identification midId1 = new Identification("first-mid-id", 51, Tier.TIER_3);
        Identification midId2 = new Identification("second-mid-id", 51, Tier.TIER_3);
        Identification lowId = new Identification("low-id", 2, Tier.TIER_5);
        Identification[] descendingOrder = new Identification[]{topId, midId1, midId2, lowId};

        Identification[] f = new Identification[]{midId1, lowId, topId, midId2};
        idList = new ArrayList<>(Arrays.asList(f));

        Collections.sort(idList);
        for (int i = 0; i < descendingOrder.length; i++) {
            Assert.assertEquals(descendingOrder[i], idList.get(i));
        }
    }
}