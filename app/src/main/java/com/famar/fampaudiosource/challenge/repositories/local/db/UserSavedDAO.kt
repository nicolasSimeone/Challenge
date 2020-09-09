package com.famar.fampaudiosource.challenge.repositories.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.famar.fampaudiosource.challenge.model.UserSaved

@Dao
interface UserSavedDAO {

    @Insert
    fun insertUserSaved(userSaved: UserSaved)

    @Query("SELECT * FROM usersSaved")
    fun getAll(): List<UserSaved>

    @Delete
    fun deleteUser(userSaved: UserSaved)
}