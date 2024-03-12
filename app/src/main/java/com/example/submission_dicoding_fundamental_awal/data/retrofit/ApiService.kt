package com.example.submission_dicoding_fundamental_awal.data.retrofit

import com.example.submission_dicoding_fundamental_awal.data.response.GithubResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("search/users")
    fun getUser(
        @Query("q") login : String
    ): Call<GithubResponse>
}