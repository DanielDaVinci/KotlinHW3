package com.example.kotlinhw3.api.providers

import com.example.kotlinhw3.api.requests.QuestionsRequest
import com.example.kotlinhw3.models.QuestionsResponse

class QuestionsProvider(private val request: QuestionsRequest)
{
    suspend fun getItems(start : Int, end : Int) : QuestionsResponse
    {
        return request.getItems(start, end)
    }
}