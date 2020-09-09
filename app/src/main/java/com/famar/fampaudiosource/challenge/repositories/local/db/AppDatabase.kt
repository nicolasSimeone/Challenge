package com.famar.fampaudiosource.challenge.repositories.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.famar.fampaudiosource.challenge.model.UserSaved


@Database(entities = [UserSaved::class], version = 3)
abstract class AppDatabase :RoomDatabase(){
    abstract fun userSavedDAO() : UserSavedDAO

    companion object{
        @Volatile
        private var INSTANCE:AppDatabase? = null
        private val ROOM_DB_NAME = "challenges.db"

        fun getDatabase(context: Context): AppDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    ROOM_DB_NAME
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}