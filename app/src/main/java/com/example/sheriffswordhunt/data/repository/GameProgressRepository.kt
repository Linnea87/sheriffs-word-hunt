package com.example.sheriffswordhunt.data.repository

interface GameProgressRepository {

    fun isCaseUnlocked(caseId: Int): Boolean

    fun unlockCase(caseId: Int)
}