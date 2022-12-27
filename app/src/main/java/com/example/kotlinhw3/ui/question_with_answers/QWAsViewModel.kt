package com.example.kotlinhw3.ui.question_with_answers

import android.util.Log
import androidx.lifecycle.*
import com.example.kotlinhw3.StatusLoad
import com.example.kotlinhw3.models.AnswersResponse
import com.example.kotlinhw3.models.QuestionsResponse
import com.example.kotlinhw3.utils.connectors.AnswersConnector
import com.example.kotlinhw3.utils.connectors.QuestionsConnector
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class QWAsViewModel(
    private val state: SavedStateHandle
) : ViewModel() {
    private val questionsKey = "ANSWERS"
    var answers: MutableLiveData<ArrayList<AnswersResponse.Item>> = state.getLiveData(questionsKey, arrayListOf())

    private val statusKey = "STATUS"
    var status: MutableLiveData<StatusLoad> = state.getLiveData(statusKey, StatusLoad.SUCCESS)

    fun addItems(question_id: Int)
    {
        viewModelScope.launch {
            answers.value = getItems(question_id)
        }
    }

    private suspend fun getItems(question_id: Int) : ArrayList<AnswersResponse.Item>{

        status.value = StatusLoad.LOADING
        try {
            val request = withContext(Dispatchers.IO) {
                AnswersConnector.provider().getItems(question_id)
            }
            status.value = StatusLoad.SUCCESS

            return request.data
        } catch (error: Throwable) {
            Log.d("HW3:ERRORS:QWAs", error.toString())
            status.value = StatusLoad.ERROR
        }

        return arrayListOf()
    }
}