package com.example.kotlinhw3.ui.profile

import android.util.Log
import androidx.lifecycle.*
import com.example.kotlinhw3.StatusLoad
import com.example.kotlinhw3.models.ProfileResponse
import com.example.kotlinhw3.models.QuestionsResponse
import com.example.kotlinhw3.utils.connectors.ProfileConnector
import com.example.kotlinhw3.utils.connectors.QuestionsConnector
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileViewModel(
    private val state: SavedStateHandle
) : ViewModel() {

    private val profileKey = "PROFILE"
    var profile: MutableLiveData<ProfileResponse.Item> = state.getLiveData(profileKey, ProfileResponse.Item())

    private val statusKey = "STATUS"
    var status: MutableLiveData<StatusLoad> = state.getLiveData(statusKey, StatusLoad.SUCCESS)

    fun setProfile(profile_id : Int) {
        viewModelScope.launch {
            profile.value = getItem(profile_id)
        }
    }


    private suspend fun getItem(profile_id : Int) : ProfileResponse.Item {

        status.value = StatusLoad.LOADING
        try {
            val request = withContext(Dispatchers.IO) {
                ProfileConnector.provider().getItem(profile_id)
            }
            status.value = StatusLoad.SUCCESS

            return request.data
        } catch (error: Throwable) {
            Log.d("HW3:ERRORS:PROFILE", error.toString())
            status.value = StatusLoad.ERROR
        }

        return ProfileResponse.Item()
    }
}