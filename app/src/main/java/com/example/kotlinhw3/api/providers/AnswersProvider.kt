package com.example.kotlinhw3.api.providers

import com.example.kotlinhw3.api.requests.AnswersRequest
import com.example.kotlinhw3.models.AnswersResponse


class AnswersProvider(private val request: AnswersRequest)
{
    suspend fun getItems(question_id : Int) : AnswersResponse
    {
        return request.getItems(question_id)
    }
}