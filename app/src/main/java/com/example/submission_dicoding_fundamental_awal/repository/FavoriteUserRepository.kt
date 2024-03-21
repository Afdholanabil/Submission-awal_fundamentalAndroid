package com.example.submission_dicoding_fundamental_awal.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.submission_dicoding_fundamental_awal.database.FavoriteUser
import com.example.submission_dicoding_fundamental_awal.database.FavoriteUserDao
import com.example.submission_dicoding_fundamental_awal.database.FavoriteUserRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavoriteUserRepository(application: Application) {
    private val mFavoriteUserDao : FavoriteUserDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = FavoriteUserRoomDatabase.getDatabase(application)
        mFavoriteUserDao = db.favDao()
    }

    fun getAllFavUser(): LiveData<List<FavoriteUser>> = mFavoriteUserDao.getAllFavoriteUser()

    fun getFavoriteUserByUsername(username: String): LiveData<FavoriteUser> = mFavoriteUserDao.getFavoriteUserByUsername(username)


    fun insert(favoriteUser: FavoriteUser){
        executorService.execute { mFavoriteUserDao.insert(favoriteUser) }
    }

    fun delete(favoriteUser: FavoriteUser){
        executorService.execute { mFavoriteUserDao.deleteFavoriteUserByUsername(favoriteUser) }
    }

    fun update(favoriteUser: FavoriteUser){
        executorService.execute { mFavoriteUserDao.update(favoriteUser) }
    }
}