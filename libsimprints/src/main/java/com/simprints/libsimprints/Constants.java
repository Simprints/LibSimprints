package com.simprints.libsimprints;

import android.net.Uri;

/**
 * This class contains base and useful constants and the contract for the content provider
 */
public class Constants {

    // gender id constants
    final static public int MALE = 0;
    final static public int FEMALE = 1;
    final static public int OTHER = 2;

    // finger id constants
    final static public int RIGHT_INDEX = 0;
    final static public int RIGHT_THUMB = 1;
    final static public int LEFT_THUMB  = 2;
    final static public int LEFT_INDEX  = 3;

    // content provider
    public static final String PACKAGE = "com.simprints.id";
    public static final String AUTHORITY = PACKAGE + ".PersonsProvider";

    public static final Uri PERSONS_URI =
            Uri.parse("content://" + AUTHORITY + "/persons");
    public static final String PERSONS_CONTENT_TYPE =
            "vnd.android.cursor.dir/vnd.simprints.id";
    public static final String PERSONS_CONTENT_ITEM =
            "vnd.android.cursor.item/vnd.simprints.id";

    public static final String PERMISSION_READ = AUTHORITY + ".READ";
    public static final String PERMISSION_WRITE = AUTHORITY + ".WRITE";

    public static final String INTENT_PERSONS_UPDATED = PACKAGE + ".INTENT_PERSONS_UPDATED";

    // persons table name
    public static final String TABLE_NAME = "PERSONS";
    public static final String DEFAULT_SORT_ORDER = "_ID";

    // persons column names
    public static final String COLUMN_ROWID = "_ID";
    public static final String COLUMN_GUID = "GUID";
    public static final String COLUMN_APIKEY = "APIKEY";
    public static final String COLUMN_RIGHT_INDEX = "RIGHT_INDEX";
    public static final String COLUMN_RIGHT_THUMB = "RIGHT_THUMB";
    public static final String COLUMN_LEFT_INDEX = "LEFT_INDEX";
    public static final String COLUMN_LEFT_THUMB = "LEFT_THUMB";
    public static final String COLUMN_CAPTURED = "CAPTURED";
    public static final String COLUMN_SYNCED = "SYNCED";
    public static final String COLUMN_STATUS = "STATUS";
    public static final String COLUMN_LATITUDE = "LATITUDE";
    public static final String COLUMN_LONGITUDE = "LONGITUDE";
}
