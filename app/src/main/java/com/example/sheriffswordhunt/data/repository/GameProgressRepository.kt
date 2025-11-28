package com.example.sheriffswordhunt.data.repository

interface GameProgressRepository {

    fun isCaseUnlocked(caseId: Int): Boolean

    fun unlockCase(caseId: Int)

    fun saveCurrentQuestion(caseId: Int, questionIndex: Int)
    fun getSavedQuestion(caseId: Int): Int
}