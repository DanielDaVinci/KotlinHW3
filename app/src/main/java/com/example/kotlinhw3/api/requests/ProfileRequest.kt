package com.example.kotlinhw3.api.requests

import com.example.kotlinhw3.models.ProfileResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProfileRequest
{
    @GET("profile/get/{profile_id}")
    suspend fun getItem(
        @Path("profile_id") id: Int,
    ) : ProfileResponse

    companion object
    {
        fun createRequest(baseUrl: String): ProfileRequest
        {
            val loggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            val client = OkHttpClient.Builder().apply {
                addNetworkInterceptor(loggingInterceptor)
            }.build()

            val retrofit = Retrofit.Builder().apply {
                client(client)
                addConverterFactory(GsonConverterFactory.create())
                baseUrl(baseUrl)
            }.build()

            return retrofit.create(ProfileRequest::class.java)
        }
    }
}