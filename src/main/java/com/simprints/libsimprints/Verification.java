package com.simprints.libsimprints;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

@SuppressWarnings({"unused", "WeakerAccess"})
public class Verification implements Parcelable {

    private int confidence;
    private Tier tier;
    private String guid;


    /**
     * Empty Constructor
     */
    public Verification() {

    }

    /**
     * This constructor creates a new verification
     *
     * @param confidence An int containing the (matching) confidence
     * @param tier       The tier score derived from the confidence
     * @param guid       Global unique id of the verified person
     */
    public Verification(int confidence, @NonNull Tier tier, @NonNull String guid) {
        this.confidence = confidence;
        this.tier = tier;
        this.guid = guid;
    }

    protected Verification(Parcel in) {
        confidence = in.readInt();
        tier = Tier.values()[in.readInt()];
        guid = in.readString();
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
        dest.writeInt(this.confidence);
        dest.writeInt(this.tier.ordinal());
        dest.writeString(guid);
    }

    public float getConfidence() {
        return confidence;
    }

    public void setConfidence(int confidence) {
        this.confidence = confidence;
    }

    @NonNull
    public Tier getTier() {
        return tier;
    }

    public void setTier(@NonNull Tier tier) {
        this.tier = tier;
    }

    @NonNull
    public String getGuid() {
        return guid;
    }

    public void setGuid(@NonNull String guid) {
        this.guid = guid;
    }
}
