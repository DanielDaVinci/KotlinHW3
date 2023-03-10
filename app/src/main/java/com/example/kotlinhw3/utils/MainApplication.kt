package com.example.kotlinhw3.utils

import android.app.Application
import com.example.kotlinhw3.utils.connectors.AnswersConnector
import com.example.kotlinhw3.utils.connectors.ProfileConnector
import com.example.kotlinhw3.utils.connectors.QuestionsConnector

class MainApplication : Application() {

    override fun onCreate()
    {
        super.onCreate()
        QuestionsConnector.initialize(this)
        ProfileConnector.initialize(this)
        AnswersConnector.initialize(this)
    }
}