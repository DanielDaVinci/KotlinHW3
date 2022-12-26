package com.example.kotlinhw3.ui.new

import androidx.lifecycle.*
import com.example.kotlinhw3.StatusLoad
import com.example.kotlinhw3.models.QuestionsResponse
import com.example.kotlinhw3.utils.MainApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewQuestionsViewModel(private val state: SavedStateHandle) : ViewModel() {

    private val questionsKey = "NEW_QUESTIONS"
    var questions: MutableLiveData<ArrayList<QuestionsResponse.Item>> = state.getLiveData(questionsKey, arrayListOf())

    private val statusKey = "STATUS"
    var status: MutableLiveData<StatusLoad> = state.getLiveData(statusKey, StatusLoad.SUCCESS)

    fun addItems(count: Int = 10)
    {
        viewModelScope.launch {
            val offset = questions.value?.size ?: 0

            questions.value = getItems(offset, count, "n")
            questions.value?.addAll(questions.value!!)
        }
    }

    private suspend fun getItems(start: Int, end: Int, statusQuestions: String) : ArrayList<QuestionsResponse.Item>{

        status.value = StatusLoad.LOADING
        try {
            val request = withContext(Dispatchers.IO) {
                MainApplication.questionsConnector.provider().getItems(start, end, statusQuestions)
            }
            status.value = StatusLoad.SUCCESS

            return request.data
        } catch (error: Throwable) {
            status.value = StatusLoad.ERROR
        }

        return arrayListOf()
    }
}