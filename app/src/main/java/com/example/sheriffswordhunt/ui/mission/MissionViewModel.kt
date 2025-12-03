package com.example.sheriffswordhunt.ui.mission

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sheriffswordhunt.data.model.MissionCase
import com.example.sheriffswordhunt.data.model.MissionQuestion
import com.example.sheriffswordhunt.data.repository.GameProgressRepository
import com.example.sheriffswordhunt.data.repository.MissionRepository

// ========== VIEWMODEL: MISSION LOGIC ================================================
// Holds mission state: current case, questions, answers, unlocks, and bandit capture.

class MissionViewModel(
    private val repository: MissionRepository,
    private val gameProgressRepository: GameProgressRepository
) : ViewModel() {

    // ========== LIVE DATA =============================================

    private val _currentCase = MutableLiveData<MissionCase>()
    val currentCase: LiveData<MissionCase> = _currentCase

    private val _currentQuestion = MutableLiveData<MissionQuestion>()
    val currentQuestion: LiveData<MissionQuestion> = _currentQuestion

    private val _answerFeedback = MutableLiveData<String>()
    val answerFeedback: LiveData<String> = _answerFeedback

    private val _caseUnlocked = MutableLiveData<Boolean>()
    val caseUnlocked: LiveData<Boolean> = _caseUnlocked

    private val _banditCaptured = MutableLiveData<Boolean>()
    val banditCaptured: LiveData<Boolean> = _banditCaptured

    // ========== INTERNAL STATE =============================================

    private var questions: List<MissionQuestion> = emptyList()
    private var currentIndex: Int = 0
    private var correctAnswers: Int = 0
    private var currentCaseId: Int = 1

    // ========== PUBLIC API =============================================

    fun loadCase(caseId: Int) {
        currentCaseId = caseId

        val case = repository.getCaseById(caseId)
            ?: error("Case with id $caseId not found")

        _currentCase.value = case

        questions = repository.getQuestionsForCase(caseId)
        currentIndex = 0
        correctAnswers = 0

        if (questions.isNotEmpty()) {
            _currentQuestion.value = questions[currentIndex]
        }
        _caseUnlocked.value = false
        _banditCaptured.value = false
    }

    fun submitAnswer(answer: String) {
        val question = _currentQuestion.value ?: return
        val isCorrect = answer == question.correctAnswer

        if (!isCorrect) {
            _answerFeedback.value = question.feedbackIncorrect
            return
        }

        correctAnswers++
        _answerFeedback.value = question.feedbackCorrect

        if (correctAnswers == 3) {
            val nextCaseId = currentCaseId + 1
            if (repository.getCaseById(nextCaseId) != null) {
                gameProgressRepository.unlockCase(nextCaseId)
                _caseUnlocked.value = true
            }
        }

        val isLastQuestion = currentIndex == questions.lastIndex

        // Save progress (count answered questions)
        val answeredCount = if (isLastQuestion) {
            questions.size
        } else {
            currentIndex + 1
        }
        gameProgressRepository.saveCurrentQuestion(currentCaseId, answeredCount)

        // Next question logic
        if (!isLastQuestion) {
            showNextQuestion()
        }

        if (correctAnswers == questions.size) {
            _banditCaptured.value = true
        }
    }

    fun loadSavedProgress(savedAnsweredCount: Int) {
        if (questions.isEmpty()) return

        val total = questions.size
        val answered = savedAnsweredCount.coerceIn(0, total)

        correctAnswers = answered

        currentIndex = if (answered >= total) {
            total - 1
        } else {
            answered
        }

        _currentQuestion.value = questions[currentIndex]
    }

    // ========== HELPERS =============================================

    private fun showNextQuestion() {
        if (currentIndex < questions.lastIndex) {
            currentIndex++
            _currentQuestion.value = questions[currentIndex]
        }
    }
}