package com.example.kotlinhw3.utils.connectors

import android.content.Context
import com.example.kotlinhw3.R
import com.example.kotlinhw3.api.providers.AnswersProvider
import com.example.kotlinhw3.api.requests.AnswersRequest

object AnswersConnector
{
    private lateinit var request : AnswersRequest

    fun initialize(context: Context)
    {
        request = AnswersRequest.createRequest(context.applicationContext.getString(R.string.base_url))
    }

    fun provider(): AnswersProvider = AnswersProvider(request)
}
