package com.example.submission_dicoding_fundamental_awal.database

import android.content.Context
import android.os.Build.VERSION
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FavoriteUser::class], version = 1)
abstract class FavoriteUserRoomDatabase : RoomDatabase() {
    abstract fun favDao(): FavoriteUserDao
    companion object {
        @Volatile
        private var INSTANCE : FavoriteUserRoomDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): FavoriteUserRoomDatabase {
            if (INSTANCE == null) {
                synchronized(FavoriteUserRoomDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,FavoriteUserRoomDatabase::class.java,"favorite_user_database")
                        .build()
                }
            }
            return INSTANCE as FavoriteUserRoomDatabase
        }

    }

}