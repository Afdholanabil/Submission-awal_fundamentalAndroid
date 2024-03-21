package com.example.submission_dicoding_fundamental_awal.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.submission_dicoding_fundamental_awal.util.Event
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class FavoriteUser (
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "username")
    var username: String = "" ,

    @ColumnInfo(name = "avatarUrl")
    var avatarUrl: String? = null

): Parcelable


