package com.example.sheriffswordhunt.data.repository

import com.example.sheriffswordhunt.data.model.MissionQuestion

interface MissionRepository {

    fun getQuestionsForCase(caseId: Int): List<MissionQuestion>
}