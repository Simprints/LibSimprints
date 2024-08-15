package com.simprints.libsimprints;

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@SuppressWarnings("WeakerAccess", "unused")
@Parcelize
data class RefusalForm(
    val reason: String,
    val extra: String,
) : Parcelable