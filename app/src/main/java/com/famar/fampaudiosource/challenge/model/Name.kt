package com.famar.fampaudiosource.challenge.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Name (
    val title:String? ="",
    val first:String? ="",
    val last:String? =""
): Parcelable
