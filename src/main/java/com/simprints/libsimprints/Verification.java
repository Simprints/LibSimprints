package com.simprints.libsimprints;

import android.os.Parcel;
import android.os.Parcelable;

@SuppressWarnings("unused")
public class Verification implements Parcelable {

    private boolean guidExists;
    private int confidence;
    private Tier tier;

    /**
     * Empty Constructor
     */
    public Verification() {

    }

    /**
     * This constructor creates a new identification
     *
     * @param guidExists A boolean containing whether the guid exists in the database
     * @param confidence An int containing the (matching) confidence
     */
    public Verification(boolean guidExists, int confidence, Tier tier) {
        this.guidExists = guidExists;
        this.confidence = confidence;
        this.tier = tier;
    }

    protected Verification(Parcel in) {
        guidExists = in.readByte() != 0;
        confidence = in.readInt();
        tier = Tier.values()[in.readInt()];
    }

    public static final Creator<Verification> CREATOR = new Creator<Verification>() {
        @Override
        public Verification createFromParcel(Parcel in) {
            return new Verification(in);
        }

        @Override
        public Verification[] newArray(int size) {
            return new Verification[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (this.guidExists ? 1 : 0));
        dest.writeInt(this.confidence);
        dest.writeInt(this.tier.ordinal());
    }

    public boolean getGuidExists() {
        return guidExists;
    }

    public void setGuidExists(boolean guidExists) {
        this.guidExists = guidExists;
    }

    public float getConfidence() {
        return confidence;
    }

    public void setConfidence(int confidence) {
        this.confidence = confidence;
    }

    public Tier getTier() {
        return tier;
    }

    public void setTier(Tier tier) {
        this.tier = tier;
    }
}
