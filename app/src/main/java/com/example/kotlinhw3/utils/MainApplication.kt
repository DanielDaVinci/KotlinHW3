package com.example.kotlinhw3.utils

import android.app.Application
import com.example.kotlinhw3.utils.connectors.AnswersConnector
import com.example.kotlinhw3.utils.connectors.ProfileConnector
import com.example.kotlinhw3.utils.connectors.QuestionsConnector

class MainApplication : Application() {

    companion object{
        val profileConnector = ProfileConnector
        val questionsConnector = QuestionsConnector
        val answersConnector = AnswersConnector
    }

    override fun onCreate()
    {
        super.onCreate()
        profileConnector.initialize(this)
        questionsConnector.initialize(this)
        answersConnector.initialize(this)
    }
}