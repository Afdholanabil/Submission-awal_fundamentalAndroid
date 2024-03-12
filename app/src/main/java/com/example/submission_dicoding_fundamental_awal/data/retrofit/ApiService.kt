package com.example.submission_dicoding_fundamental_awal.data.retrofit

import com.example.submission_dicoding_fundamental_awal.data.response.GithubResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("search/users?q={username}")
    fun getUser(
        @Path("username") id : String
    ): Call<GithubResponse>
}