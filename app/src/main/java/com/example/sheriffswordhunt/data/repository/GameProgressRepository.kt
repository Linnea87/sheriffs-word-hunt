package com.example.sheriffswordhunt.data.repository

// ========== REPOSITORY CONTRACT: GAME PROGRESS ============
// Defines how the app stores and retrieves player progress.

interface GameProgressRepository {

    fun isCaseUnlocked(caseId: Int): Boolean

    fun unlockCase(caseId: Int)

    fun saveCurrentQuestion(caseId: Int, questionIndex: Int)

    fun getSavedQuestion(caseId: Int): Int
}