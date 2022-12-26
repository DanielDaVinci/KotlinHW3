package com.example.kotlinhw3.utils.connectors

import android.content.Context
import com.example.kotlinhw3.R
import com.example.kotlinhw3.api.providers.ProfileProvider
import com.example.kotlinhw3.api.requests.ProfileRequest

object ProfileConnector
{
    private lateinit var request : ProfileRequest

    fun initialize(context: Context)
    {
        request = ProfileRequest.createRequest(context.applicationContext.getString(R.string.base_url))
    }

    fun provider(): ProfileProvider = ProfileProvider(request)
}
