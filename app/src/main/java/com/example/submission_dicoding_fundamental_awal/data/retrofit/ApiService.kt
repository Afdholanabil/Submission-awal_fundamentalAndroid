package com.example.submission_dicoding_fundamental_awal.data.retrofit

import com.example.submission_dicoding_fundamental_awal.data.response.DetailUserResponse
import com.example.submission_dicoding_fundamental_awal.data.response.GithubResponse
import com.example.submission_dicoding_fundamental_awal.data.response.followItems
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("search/users")
    fun getUser(
        @Query("q") login : String
    ): Call<GithubResponse>

    @GET("users/{username}")
    fun getDetailUser(
        @Path("username") username : String
    ): Call<DetailUserResponse>

    @GET("users/{username}/following")
    fun getFollowing(
        @Path("username") username: String
    ): Call<List<followItems>>

    @GET("users/{username}/followers")
    fun getFollowers(
        @Path("username") username: String
    ): Call<List<followItems>>

}