package com.example.kotlinhw3.api.providers

import com.example.kotlinhw3.api.requests.ProfileRequest
import com.example.kotlinhw3.models.ProfileResponse

class ProfileProvider(private val request: ProfileRequest)
{
    suspend fun getItem(profile_id : Int) : ProfileResponse
    {
        return request.getItem(profile_id)
    }
}