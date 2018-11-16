package com.simprints.libsimprints;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;

@SuppressWarnings({"unused", "WeakerAccess"})
public class Registration implements Parcelable {

    private String guid;
    private Map<FingerIdentifier, byte[]> templates;

    /**
     * This constructor creates a new registration
     *
     * @param guid Global user id
     */
    public Registration(String guid) {
        this.guid = guid;
        templates = new HashMap<>();
    }

    protected Registration(Parcel in) {
        this.guid = in.readString();
        int nbOfTemplates = in.readInt();
        this.templates = new HashMap<>(nbOfTemplates);
        for (int i = 0; i < nbOfTemplates; i++) {
            FingerIdentifier fingerId = FingerIdentifier.values()[in.readInt()];
            byte[] fingerTemplate = new byte[in.readInt()];
            in.readByteArray(fingerTemplate);
            this.templates.put(fingerId, fingerTemplate);
        }
    }

    public static final Creator<Registration> CREATOR = new Creator<Registration>() {
        @Override
        public Registration createFromParcel(Parcel in) {
            return new Registration(in);
        }

        @Override
        public Registration[] newArray(int size) {
            return new Registration[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(guid);
        dest.writeInt(templates.size());
        for (Map.Entry<FingerIdentifier, byte[]> e : templates.entrySet()) {
            dest.writeInt(e.getKey().ordinal());
            dest.writeInt(e.getValue().length);
            dest.writeByteArray(e.getValue());
        }
    }

    public String getGuid() {
        return guid;
    }

    public void setTemplate(FingerIdentifier fingerId, @NonNull byte[] fingerTemplate) {
        this.templates.put(fingerId, fingerTemplate);
    }

    public byte[] getTemplate(FingerIdentifier fingerId) {
        if (templates.containsKey(fingerId)) {
            return templates.get(fingerId);
        } else {
            return new byte[0];
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Registration)) {
            return false;
        }
        Registration other = (Registration) o;
        if (!guid.equals(other.guid)) {
            return false;
        }
        for (FingerIdentifier fingerId : FingerIdentifier.values()) {
            if (!Arrays.equals(templates.get(fingerId), other.templates.get(fingerId))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = guid.hashCode();
        for (FingerIdentifier fingerId : FingerIdentifier.values()) {
            hash = hash * 17 + Arrays.hashCode(templates.get(fingerId));
        }
        return hash;
    }
}
