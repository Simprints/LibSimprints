package com.simprints.libsimprints;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

@SuppressWarnings("unused")
public class Identification implements Parcelable, Comparable<Identification> {

    private String guid;
    private int confidence;
    private Tier tier;

    /**
     * Empty constructor
     */
    public Identification() {

    }

    /**
     * This constructor creates a new identification
     *
     * @param guid       A string containing the guid
     * @param confidence An int containing the (matching) confidence
     */
    public Identification(String guid, int confidence, Tier tier) {
        this.guid = guid;
        this.confidence = confidence;
        this.tier = tier;
    }

    protected Identification(Parcel in) {
        guid = in.readString();
        confidence = in.readInt();
        tier = Tier.values()[in.readInt()];
    }

    public static final Creator<Identification> CREATOR = new Creator<Identification>() {
        @Override
        public Identification createFromParcel(Parcel in) {
            return new Identification(in);
        }

        @Override
        public Identification[] newArray(int size) {
            return new Identification[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.guid);
        dest.writeInt(this.confidence);
        dest.writeInt(this.tier.ordinal());
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
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

    @Override
    public int compareTo(@NonNull Identification otherId) {
        if (confidence == otherId.confidence) {
            return 0;
        } else if (confidence < otherId.confidence) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof Identification) {
            Identification other = (Identification)o;
            return (guid.equals(other.guid) &&
                    confidence == other.confidence &&
                    tier.equals(other.tier));
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = guid.hashCode();
        hash = hash * 17 + confidence;
        hash = hash * 17 + tier.hashCode();
        return hash;
    }
}
