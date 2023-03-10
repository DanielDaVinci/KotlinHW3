package com.example.kotlinhw3.utils.connectors

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.example.kotlinhw3.R
import com.example.kotlinhw3.api.providers.QuestionsProvider
import com.example.kotlinhw3.api.requests.QuestionsRequest

@SuppressLint("StaticFieldLeak")
object QuestionsConnector {

    private lateinit var request : QuestionsRequest

    fun initialize(context: Context)
    {
        request = QuestionsRequest.createRequest(context.applicationContext.getString(R.string.base_url))
    }

    fun provider(): QuestionsProvider = QuestionsProvider(request)
}
