package com.simprints.libsimprints;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * This class represents a parcelable template with some meta data
 */
public class TemplateWithMetaData implements Parcelable {

    private String guid;
    private int finger;
    private int gender;
    private byte[] template;

    /**
     * This constructor creates a new instance of a template with some (optional) meta data
     *
     * @param guid A string contained a guid
     * @param template A byte array containing a template
     * @param finger An integer representing which finger
     * @param gender An integer representing which gender
     */
    public TemplateWithMetaData(String guid, byte[] template, int finger, int gender) {
        this.guid = guid;
        this.template = template;
        this.finger = finger;
        this.gender = gender;
    }

    protected TemplateWithMetaData(Parcel in) {
        guid = in.readString();
        template = in.createByteArray();
        finger = in.readInt();
        gender = in.readInt();
    }

    public static final Creator<TemplateWithMetaData> CREATOR = new Creator<TemplateWithMetaData>() {
        @Override
        public TemplateWithMetaData createFromParcel(Parcel in) {
            return new TemplateWithMetaData(in);
        }

        @Override
        public TemplateWithMetaData[] newArray(int size) {
            return new TemplateWithMetaData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(guid);
        dest.writeByteArray(template);
        dest.writeInt(finger);
        dest.writeInt(gender);
    }

    public String getGuid() { return guid; }

    public void setGuid(String guid) { this.guid = guid; }

    public byte[] getTemplate() { return template; }

    public void setTemplate(byte[] template) { this.template = template; }

    public int getFinger() { return finger; }

    public void setFinger(int finger) { this.finger = finger; }

    public int getGender() { return gender; }

    public void setGender(int gender) { this.gender = gender; }
}
