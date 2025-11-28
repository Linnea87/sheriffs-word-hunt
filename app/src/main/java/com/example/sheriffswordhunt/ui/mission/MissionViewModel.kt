package com.example.sheriffswordhunt.ui.mission

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sheriffswordhunt.data.model.MissionCase
import com.example.sheriffswordhunt.data.model.MissionQuestion
import com.example.sheriffswordhunt.data.repository.GameProgressRepository
import com.example.sheriffswordhunt.data.repository.MissionRepository

class MissionViewModel(
    private val repository: MissionRepository,
    private val gameProgressRepository: GameProgressRepository
) : ViewModel() {
    private val _currentCase = MutableLiveData<MissionCase>()
    val currentCase: LiveData<MissionCase> = _currentCase

    private val _currentQuestion = MutableLiveData<MissionQuestion>()
    val currentQuestion: LiveData<MissionQuestion> = _currentQuestion

    private var questions: List<MissionQuestion> = emptyList()
    private var currentIndex: Int = 0
    private var correctAnswers: Int = 0
    private var currentCaseId: Int = 1

    private val _answerFeedback = MutableLiveData<String>()
    val answerFeedback: LiveData<String> = _answerFeedback

    private val _caseUnlocked = MutableLiveData<Boolean>()
    val caseUnlocked: LiveData<Boolean> = _caseUnlocked

    private val _banditCaptured = MutableLiveData<Boolean>()
    val banditCaptured: LiveData<Boolean> = _banditCaptured

    fun loadCase(caseId: Int) {
        currentCaseId = caseId

        val case = repository.getCaseById(caseId)
            ?: error("Case with id $caseId not found")

        _currentCase.value = case

        questions = repository.getQuestionsForCase(caseId)
        currentIndex = 0
        correctAnswers = 0
        _caseUnlocked.value = false
        _banditCaptured.value = false

        if (questions.isNotEmpty()) {
            _currentQuestion.value = questions[currentIndex]
        }
    }

    private fun showNextQuestion() {
        if (currentIndex < questions.lastIndex) {
            currentIndex++
            _currentQuestion.value = questions[currentIndex]
        }
    }

    fun submitAnswer(answer: String) {
        val question = _currentQuestion.value ?: return

        val isCorrect = answer == question.correctAnswer

        if (isCorrect) {
            correctAnswers++
            _answerFeedback.value = question.feedbackCorrect

            if ((_caseUnlocked.value != true) && correctAnswers >= 3) {
                _caseUnlocked.value = true

                val nextCaseId = currentCaseId + 1
                val nextCaseExists = repository.getCaseById(nextCaseId) != null
                if (nextCaseExists) {
                    gameProgressRepository.unlockCase(nextCaseId)
                }
            }

            if ((_banditCaptured.value != true) && correctAnswers == questions.size) {
                _banditCaptured.value = true
            }

            val isLastQuestion = currentIndex == questions.lastIndex
            if (!isLastQuestion) {
                showNextQuestion()

                gameProgressRepository.saveCurrentQuestion(currentCaseId, currentIndex)
            } else {

            gameProgressRepository.saveCurrentQuestion(currentCaseId, currentIndex)
        }

        } else {
            _answerFeedback.value = question.feedbackIncorrect
        }
    }

    fun loadSavedProgress(savedIndex: Int) {
        if (savedIndex in questions.indices) {
            currentIndex = savedIndex
            _currentQuestion.value = questions[currentIndex]
        }
    }
}