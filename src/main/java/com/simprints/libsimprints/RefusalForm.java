package com.simprints.libsimprints;

import android.os.Parcel;
import android.os.Parcelable;

@SuppressWarnings({"WeakerAccess", "unused"})
public class RefusalForm implements Parcelable {
    private String reason;
    private String extra;

    public RefusalForm(String reason, String extra) {
        this.reason = reason;
        this.extra = extra;
    }

    protected RefusalForm(Parcel in) {
        this.reason = in.readString();
        this.extra = in.readString();
    }

    public String getReason() {
        return this.reason;
    }

    public String getExtra() {
        return this.extra;
    }

    public static final Creator<RefusalForm> CREATOR = new Creator<RefusalForm>() {
        @Override
        public RefusalForm createFromParcel(Parcel in) {
            return new RefusalForm(in);
        }

        @Override
        public RefusalForm[] newArray(int size) {
            return new RefusalForm[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(reason);
        dest.writeString(extra);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof RefusalForm) {
            RefusalForm other = (RefusalForm) o;
            return (reason.equals(other.reason) &&
                    extra.equals(other.extra));
        }
        return false;
    }
}
