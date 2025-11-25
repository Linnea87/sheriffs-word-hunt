package com.example.sheriffswordhunt.ui.mission

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sheriffswordhunt.R
import com.example.sheriffswordhunt.data.model.MissionCase
import com.example.sheriffswordhunt.data.model.MissionQuestion
import com.example.sheriffswordhunt.data.repository.MissionRepository
import com.example.sheriffswordhunt.data.repository.MissionRepositoryImpl
import com.example.sheriffswordhunt.databinding.ActivityMissionBinding

class MissionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMissionBinding
    private val repository: MissionRepository = MissionRepositoryImpl()
    private val currentCaseId: Int = 1
    private lateinit var questions: List<MissionQuestion>
    private var currentIndex: Int = 0
    private lateinit var currentCase: MissionCase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMissionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.heroSection.imgHero.setImageResource(R.drawable.hero_mission)
         currentCase = repository.getCaseById(currentCaseId)
             ?: error("Case with id $currentCaseId not found")

        binding.tvMissionTitle.text = currentCase.title
        binding.tvMissionSubtitle.text = currentCase.subtitle


        questions = repository.getQuestionsForCase(currentCaseId)

        if (questions.isNotEmpty()) {
            showQuestion()
        }
    }
    private fun showQuestion() {
        val question = questions[currentIndex]
        binding.tvQuestion.text = question.prompt

        binding.btnOption1.text = question.options[0]
        binding.btnOption2.text = question.options[1]
        binding.btnOption3.text = question.options[2]
    }
}