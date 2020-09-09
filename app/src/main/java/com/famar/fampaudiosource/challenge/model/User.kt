package com.famar.fampaudiosource.challenge.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User (
    val gender :String? = "",
    val name: Name = Name(),
    val  email: String? = "",
    val phone: String? = "",
    val cell: String? = "",
    val picture: Picture = Picture()
):Parcelable
