package com.example.submission_dicoding_fundamental_awal.data.retrofit

import com.google.gson.internal.GsonBuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {

    companion object {
        fun getApiService() : ApiService {
            val authInterceptor = Interceptor { chain ->
                val req = chain.request()
                val requestHeader = req.newBuilder()
                    .addHeader("Authorization", "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")
                    .build()
                chain.proceed(requestHeader)
            }

            val client =OkHttpClient.Builder().addInterceptor(authInterceptor).build()

            val retrofit =Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            return retrofit.create(ApiService::class.java)


        }
    }
}