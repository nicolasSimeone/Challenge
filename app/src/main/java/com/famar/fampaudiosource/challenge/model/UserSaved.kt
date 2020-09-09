package com.famar.fampaudiosource.challenge.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "usersSaved")
@Parcelize
data class UserSaved (
    @ColumnInfo(name = "email")
    val email:String?,
    @ColumnInfo(name = "picture")
    val picture:String?,
    @ColumnInfo(name = "name")
    val name :String?,
    @ColumnInfo(name = "phone")
    val phone:String?
):Parcelable
{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
