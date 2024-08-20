package com.simprints.libsimprints

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Registration(
    val guid: String,
) : Parcelable
