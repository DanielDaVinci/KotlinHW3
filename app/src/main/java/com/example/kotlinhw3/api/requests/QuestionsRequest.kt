package com.example.kotlinhw3.api.requests

import com.example.kotlinhw3.models.QuestionsResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface QuestionsRequest
{
    @GET("questions/get/")
    suspend fun getItems(
        @Query("start") start: Int,
        @Query("end") end: Int,
    ) : QuestionsResponse

    companion object
    {
        fun createRequest(baseUrl: String): QuestionsRequest
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

            return retrofit.create(QuestionsRequest::class.java)
        }
    }
}