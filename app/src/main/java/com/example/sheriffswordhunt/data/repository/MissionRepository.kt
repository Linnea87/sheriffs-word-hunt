package com.example.sheriffswordhunt.data.repository

import com.example.sheriffswordhunt.data.model.MissionCase
import com.example.sheriffswordhunt.data.model.MissionQuestion

interface MissionRepository {

    fun getCases(): List<MissionCase>
    fun getCaseById(id: Int): MissionCase?
    fun getQuestionsForCase(caseId: Int): List<MissionQuestion>
}