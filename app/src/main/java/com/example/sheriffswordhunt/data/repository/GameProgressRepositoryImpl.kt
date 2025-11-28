package com.example.sheriffswordhunt.data.repository

import android.content.SharedPreferences

class GameProgressRepositoryImpl (
    private val prefs: SharedPreferences
) : GameProgressRepository {

    companion object {
        private const val KEY_UNLOCK_PREFIX = "case_unlocked_"
    }

    override fun isCaseUnlocked(caseId: Int): Boolean {
        if (caseId == 1) return true

        return prefs.getBoolean(KEY_UNLOCK_PREFIX + caseId, false)
    }

    override fun unlockCase(caseId: Int) {
        if (caseId == 1) return

        prefs.edit()
            .putBoolean(KEY_UNLOCK_PREFIX + caseId, true)
            .apply()
    }

    override fun saveCurrentQuestion(caseId: Int, questionIndex: Int) {
        prefs.edit()
            .putInt("case_${caseId}_current_question", questionIndex)
            .apply()
    }

    override fun getSavedQuestion(caseId: Int): Int {
        return prefs.getInt("case_${caseId}_current_question", 0)
    }


}