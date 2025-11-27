package com.example.sheriffswordhunt.ui.mission

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.sheriffswordhunt.R
import com.example.sheriffswordhunt.data.model.MissionCase
import com.example.sheriffswordhunt.data.model.MissionQuestion
import com.example.sheriffswordhunt.data.repository.MissionRepository
import com.example.sheriffswordhunt.data.repository.MissionRepositoryImpl
import com.example.sheriffswordhunt.databinding.ActivityMissionBinding
import kotlin.collections.get

class MissionActivity : AppCompatActivity() {

    private val repository: MissionRepository = MissionRepositoryImpl()
    private val currentCaseId: Int = 1
    private lateinit var binding: ActivityMissionBinding

    private val viewModel: MissionViewModel by viewModels {
        MissionViewModelFactory(repository)
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMissionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.heroSection.imgHero.setImageResource(R.drawable.hero_mission)

        viewModel.currentCase.observe(this) { missionCase ->
            binding.tvMissionTitle.text = missionCase.title
            binding.tvMissionSubtitle.text = missionCase.subtitle
        }

        viewModel.currentQuestion.observe(this) { question ->
            binding.tvQuestion.text = question.prompt
            binding.btnOption1.text = question.options[0]
            binding.btnOption2.text = question.options[1]
            binding.btnOption3.text = question.options[2]
        }

        viewModel.loadCase(currentCaseId)
    }

}