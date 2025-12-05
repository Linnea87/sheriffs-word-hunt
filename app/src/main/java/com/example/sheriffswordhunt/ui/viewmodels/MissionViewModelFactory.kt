package com.example.sheriffswordhunt.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sheriffswordhunt.data.mission.MissionRepository
import com.example.sheriffswordhunt.data.progress.GameProgressRepository

// ========== VIEWMODEL FACTORY: MISSION ===============
// Creates MissionViewModel with required repositories.

class MissionViewModelFactory(
    private val missionRepository: MissionRepository,
    private val gameProgressRepository: GameProgressRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MissionViewModel::class.java)) {
            return MissionViewModel(missionRepository, gameProgressRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}