package com.example.kotlinhw3.api.providers

import android.util.Log
import com.example.kotlinhw3.api.requests.QuestionsRequest
import com.example.kotlinhw3.models.QuestionsResponse

class QuestionsProvider(private val request: QuestionsRequest)
{
    suspend fun getItems(start : Int, end : Int, status : String) : QuestionsResponse
    {
        return request.getItems(start, end, status)
    }
}