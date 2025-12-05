package com.example.sheriffswordhunt.data.mission

import com.example.sheriffswordhunt.data.models.MissionCase
import com.example.sheriffswordhunt.data.models.MissionQuestion

// ========== REPOSITORY CONTRACT: MISSION DATA ============
// Provides access to cases and their associated questions.

interface MissionRepository {

    fun getCases(): List<MissionCase>

    fun getCaseById(id: Int): MissionCase?

    fun getQuestionsForCase(caseId: Int): List<MissionQuestion>
}