package com.example.sheriffswordhunt.ui.mission

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sheriffswordhunt.data.model.MissionCase
import com.example.sheriffswordhunt.data.model.MissionQuestion
import com.example.sheriffswordhunt.data.repository.MissionRepository
import java.sql.RowId

class MissionViewModel(
    private val repository: MissionRepository
) : ViewModel() {
    private val _currentCase = MutableLiveData<MissionCase>()
    val currentCase: LiveData<MissionCase> = _currentCase

    private val _currentQuestion = MutableLiveData<MissionQuestion>()
    val currentQuestion: LiveData<MissionQuestion> = _currentQuestion

    private var questions: List<MissionQuestion> = emptyList()
    private var currentIndex: Int = 0

    fun loadCase(caseId: Int) {
        val case = repository.getCaseById(caseId)
            ?: error("Case with id $caseId not found")

        _currentCase.value = case

        questions = repository.getQuestionsForCase(caseId)
        currentIndex = 0

        if (questions.isNotEmpty()) {
            _currentQuestion.value = questions[currentIndex]
        }
    }

    fun showNextQuestion() {
        if (currentIndex < questions.lastIndex) {
            currentIndex++
            _currentQuestion.value = questions[currentIndex]
        }
    }
}