package com.simprints.libsimprints;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;

@SuppressWarnings("unused")
public class Registration implements Parcelable {

    private String guid;
    private byte[] rightIndex;
    private byte[] rightThumb;
    private byte[] leftIndex;
    private byte[] leftThumb;

    /**
     * Empty constructor
     */
    public Registration() {

    }

    /**
     * This constructor creates a new registration
     *
     * @param guid Global user id
     * @param rightIndex Right index template
     * @param rightThumb Right thumb template
     * @param leftIndex Left index template
     * @param leftThumb Left thumb template
     */
    public Registration(String guid, byte[] rightIndex, byte[] rightThumb, byte[] leftIndex, byte[] leftThumb) {
        this.guid = guid;
        if (rightIndex != null) {
            this.rightIndex = Arrays.copyOf(rightIndex, rightIndex.length);
        }
        if (rightThumb != null) {
            this.rightThumb = Arrays.copyOf(rightThumb, rightThumb.length);
        }
        if (leftIndex != null) {
            this.leftIndex = Arrays.copyOf(leftIndex, leftIndex.length);
        }
        if (leftThumb != null) {
            this.leftThumb = Arrays.copyOf(leftThumb, leftThumb.length);
        }
    }

    public void register() {

    }

    protected Registration(Parcel in) {
        this.guid = in.readString();
        this.rightIndex = new byte[in.readInt()];
        in.readByteArray(this.rightIndex);
        this.rightThumb = new byte[in.readInt()];
        in.readByteArray(this.rightThumb);
        this.leftIndex = new byte[in.readInt()];
        in.readByteArray(this.leftIndex);
        this.leftThumb = new byte[in.readInt()];
        in.readByteArray(this.leftThumb);
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
        if (rightIndex == null) {
            dest.writeInt(0);
            dest.writeByteArray(new byte[0]);
        }
        else {
            dest.writeInt(rightIndex.length);
            dest.writeByteArray(rightIndex);
        }
        if (rightThumb == null) {
            dest.writeInt(0);
            dest.writeByteArray(new byte[0]);
        }
        else {
            dest.writeInt(rightThumb.length);
            dest.writeByteArray(rightThumb);
        }
        if (leftIndex == null) {
            dest.writeInt(0);
            dest.writeByteArray(new byte[0]);
        }
        else {
            dest.writeInt(leftIndex.length);
            dest.writeByteArray(leftIndex);
        }
        if (leftThumb == null) {
            dest.writeInt(0);
            dest.writeByteArray(new byte[0]);
        }
        else {
            dest.writeInt(this.leftThumb.length);
            dest.writeByteArray(leftThumb);
        }
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public byte[] getRightIndex() {
        return rightIndex;
    }

    public void setRightIndex(byte[] rightIndex) {
        this.rightIndex = rightIndex;
    }

    public byte[] getRightThumb() {
        return rightThumb;
    }

    public void setRightThumb(byte[] rightThumb) {
        this.rightThumb = rightThumb;
    }

    public byte[] getLeftIndex() {
        return leftIndex;
    }

    public void setLeftIndex(byte[] leftIndex) {
        this.leftIndex = leftIndex;
    }

    public byte[] getLeftThumb() {
        return leftThumb;
    }

    public void setLeftThumb(byte[] leftThumb) {
        this.leftThumb = leftThumb;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof Registration) {
            Registration otherRegistration = (Registration)o;
            return guid.equals(otherRegistration.guid) &&
                    Arrays.equals(rightIndex, otherRegistration.rightIndex) &&
                    Arrays.equals(rightThumb, otherRegistration.rightThumb) &&
                    Arrays.equals(leftIndex, otherRegistration.leftIndex) &&
                    Arrays.equals(leftThumb, otherRegistration.leftThumb);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return guid.hashCode() ^
                Arrays.hashCode(rightIndex) ^
                Arrays.hashCode(rightThumb) ^
                Arrays.hashCode(leftIndex) ^
                Arrays.hashCode(leftThumb);
    }
}
