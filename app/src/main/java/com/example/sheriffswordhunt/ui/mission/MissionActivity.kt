package com.example.sheriffswordhunt.ui.mission

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.sheriffswordhunt.R
import com.example.sheriffswordhunt.data.repository.GameProgressRepository
import com.example.sheriffswordhunt.data.repository.GameProgressRepositoryImpl
import com.example.sheriffswordhunt.data.repository.MissionRepository
import com.example.sheriffswordhunt.data.repository.MissionRepositoryImpl
import com.example.sheriffswordhunt.databinding.ActivityMissionBinding

// ========== MISSION ACTIVITY ==========
// Handles mission gameplay: loading questions, checking answers,
// showing feedback, and saving player progress.
class MissionActivity : AppCompatActivity() {

    // ========== BINDING & REPOSITORIES ==========

    private lateinit var binding: ActivityMissionBinding
    private val missionRepository: MissionRepository = MissionRepositoryImpl()

    private val gameProgressRepository: GameProgressRepository by lazy {
        val prefs = getSharedPreferences("game_progress", MODE_PRIVATE)
        GameProgressRepositoryImpl(prefs)
    }

    companion object {
        const val EXTRA_CASE_ID = "extra_case_id"
    }

    // ========== VIEWMODEL ==========

    private val viewModel: MissionViewModel by viewModels {
        MissionViewModelFactory(missionRepository, gameProgressRepository)
    }

    // ========== LIFECYCLE ==========

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMissionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.heroSection.imgHero.setImageResource(R.drawable.hero_mission)

        // ========== OBSERVERS ==========

        viewModel.currentCase.observe(this) { case ->
            binding.tvMissionTitle.text = case.title
            binding.tvMissionSubtitle.text = case.subtitle
        }

        viewModel.currentQuestion.observe(this) { question ->
            binding.tvQuestion.text = question.prompt
            binding.btnOption1.text = question.options[0]
            binding.btnOption2.text = question.options[1]
            binding.btnOption3.text = question.options[2]
        }

        viewModel.answerFeedback.observe(this) { showCustomToast(it) }

        viewModel.caseUnlocked.observe(this) {
            if (it == true) showCustomToast(getString(R.string.toast_case_unlocked))
        }

        viewModel.banditCaptured.observe(this) {
            if (it == true) showCustomToast(getString(R.string.toast_bandit_captured))
        }

        // ========== BUTTON LISTENERS ==========

        binding.btnOption1.setOnClickListener {
            viewModel.submitAnswer(binding.btnOption1.text.toString())
        }

        binding.btnOption2.setOnClickListener {
            viewModel.submitAnswer(binding.btnOption2.text.toString())
        }

        binding.btnOption3.setOnClickListener {
            viewModel.submitAnswer(binding.btnOption3.text.toString())
        }

        binding.btnBack.setOnClickListener {
            finish()
        }

        // ========== LOAD CASE ==========

        val caseId = intent.getIntExtra(EXTRA_CASE_ID, 1)
        viewModel.loadCase(caseId)

        val savedIndex = gameProgressRepository.getSavedQuestion(caseId)
        viewModel.loadSavedProgress(savedIndex)
    }

    // ========== TOAST ==========

    private fun showCustomToast(message: String) {
        val layout = layoutInflater.inflate(R.layout.custom_toast, null)
        val textView = layout.findViewById<TextView>(R.id.tvToastMessage)
        textView.text = message

        @Suppress("DEPRECATION")
        val toast = Toast(this).apply {
            duration = Toast.LENGTH_SHORT
            view = layout
        }

        toast.setGravity(
            android.view.Gravity.CENTER_HORIZONTAL or android.view.Gravity.BOTTOM,
            0,
            150
        )

        toast.show()
    }
}