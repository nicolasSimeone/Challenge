package com.famar.fampaudiosource.challenge.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class results (
    var results : MutableList<User>
    ):Parcelable