package com.example.submission_dicoding_fundamental_awal.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface FavoriteUserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(favoriteUser: FavoriteUser)

    @Update
    fun update(favoriteUser: FavoriteUser)

    @Delete
    fun deleteFavoriteUserByUsername(favoriteUser: FavoriteUser)

    @Query("SELECT * from favoriteuser ORDER BY username = username")
    fun getAllFavoriteUser(): LiveData<List<FavoriteUser>>

    @Query("SELECT * FROM favoriteuser where username = :username")
    fun getFavoriteUserByUsername(username: String): LiveData<FavoriteUser>



}