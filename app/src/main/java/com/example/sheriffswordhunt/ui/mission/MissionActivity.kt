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


class MissionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMissionBinding
    private val missionRepository: MissionRepository = MissionRepositoryImpl()
    private val gameProgressRepository: GameProgressRepository by lazy {
        val prefs = getSharedPreferences("game_progress", MODE_PRIVATE)
        GameProgressRepositoryImpl(prefs)
    }

    companion object{
        const val EXTRA_CASE_ID = "extra_case_id"
    }

    private val viewModel: MissionViewModel by viewModels {
        MissionViewModelFactory(missionRepository, gameProgressRepository)
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

        binding.btnOption1.setOnClickListener {
            val answer = binding.btnOption1.text.toString()
            viewModel.submitAnswer(answer)
        }

        binding.btnOption2.setOnClickListener {
            val answer = binding.btnOption2.text.toString()
            viewModel.submitAnswer(answer)
        }

        binding.btnOption3.setOnClickListener {
            val answer = binding.btnOption3.text.toString()
            viewModel.submitAnswer(answer)
        }
         viewModel.answerFeedback.observe(this) { message ->
             showCustomToast(message)
         }

        viewModel.caseUnlocked.observe(this) { unlocked ->
            if (unlocked == true) {
                showCustomToast(getString(R.string.toast_case_unlocked))

            }
        }

        viewModel.banditCaptured.observe(this) { captured ->
            if (captured == true) {
                showCustomToast(getString(R.string.toast_bandit_captured))

            }
        }

        val caseId = intent.getIntExtra(EXTRA_CASE_ID, 1)
        viewModel.loadCase(caseId)

        val savedIndex = gameProgressRepository.getSavedQuestion(caseId)
        viewModel.loadSavedProgress(savedIndex)

    }

    private fun showCustomToast(message: String) {
        val inflater = layoutInflater
        val layout = inflater.inflate(R.layout.custom_toast, null)
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