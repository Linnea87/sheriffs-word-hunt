package com.example.sheriffswordhunt.ui.mission

import android.os.Bundle
import android.os.Looper
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.sheriffswordhunt.R
import com.example.sheriffswordhunt.data.progress.GameProgressRepository
import com.example.sheriffswordhunt.data.progress.GameProgressRepositoryImpl
import com.example.sheriffswordhunt.data.mission.MissionRepository
import com.example.sheriffswordhunt.data.mission.MissionRepositoryImpl
import com.example.sheriffswordhunt.databinding.ActivityMissionBinding
import android.os.Handler
import com.example.sheriffswordhunt.ui.common.DialogHelper
import com.example.sheriffswordhunt.ui.common.ToastHelper
import com.example.sheriffswordhunt.ui.viewmodels.MissionViewModel
import com.example.sheriffswordhunt.ui.viewmodels.MissionViewModelFactory

// ========== MISSION ACTIVITY ===================================
// Handles mission gameplay: loading questions, checking answers,
// showing feedback, and saving player progress.
class MissionActivity : AppCompatActivity() {

    // ========== BINDING & REPOSITORIES =============================================

    private lateinit var binding: ActivityMissionBinding
    private val missionRepository: MissionRepository = MissionRepositoryImpl()

    private val gameProgressRepository: GameProgressRepository by lazy {
        val prefs = getSharedPreferences("game_progress", MODE_PRIVATE)
        GameProgressRepositoryImpl(prefs)
    }

    private val dialogHandler by lazy { Handler(Looper.getMainLooper()) }
    private lateinit var dialogHelper: DialogHelper

    private val toastHelper by lazy { ToastHelper(this) }
    private var lastToastShownAt: Long = 0L

    companion object {
        const val EXTRA_CASE_ID = "extra_case_id"

        private const val TOAST_DURATION_MS = 2000L
        private const val EXTRA_DELAY_AFTER_TOAST_MS = 400L
    }

    // ========== VIEWMODEL =============================================

    private val viewModel: MissionViewModel by viewModels {
        MissionViewModelFactory(missionRepository, gameProgressRepository)
    }

    // ========== LIFECYCLE =============================================

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMissionBinding.inflate(layoutInflater)
        setContentView(binding.root)


        dialogHelper = DialogHelper(this)

        binding.heroSection.imgHero.setImageResource(R.drawable.hero_mission)

        // ========== OBSERVERS =============================================

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

        viewModel.answerFeedback.observe(this) { message ->
            toastHelper.show(message)
            lastToastShownAt = System.currentTimeMillis()
        }

        viewModel.caseUnlocked.observe(this) { unlocked ->
            if (unlocked == true) {
                runAfterToast {
                    dialogHelper.showCaseUnlockedDialog(
                        onContinue = {
                            // stay in mission, nothing extra
                        },
                        onBackToTown = {
                            finish()
                        }
                    )
                }
            }
        }

        viewModel.banditCaptured.observe(this) { captured ->
            if (captured == true) {
                val case = viewModel.currentCase.value
                val currentCaseId = case?.id ?: 1

                val messageRes = when (currentCaseId) {
                    1 -> R.string.dialog_bandit_captured_case1
                    2 -> R.string.dialog_bandit_captured_case2
                    3 -> R.string.dialog_bandit_captured_case3
                    else -> R.string.dialog_bandit_captured_generic
                }

                val nextCaseId = currentCaseId + 1
                val nextCaseExists = missionRepository.getCaseById(nextCaseId) != null

                runAfterToast {
                    dialogHelper.showBanditCapturedDialog(
                        messageRes = messageRes,
                        showContinue = nextCaseExists,
                        onContinue = {
                            val intent = intent.apply {
                                putExtra(EXTRA_CASE_ID, nextCaseId)
                            }
                            finish()
                            startActivity(intent)
                        },
                        onBackToTown = {
                            finish()
                        }
                    )
                }
            }
        }

        // ========== BUTTON LISTENERS =============================================

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

        // ========== LOAD CASE =============================================

        val caseId = intent.getIntExtra(EXTRA_CASE_ID, 1)
        viewModel.loadCase(caseId)

        val savedIndex = gameProgressRepository.getSavedQuestion(caseId)
        viewModel.loadSavedProgress(savedIndex)
    }

    private fun runAfterToast(block: () -> Unit) {
        val now = System.currentTimeMillis()

        val toastEndTime = lastToastShownAt + TOAST_DURATION_MS

        val desiredTime = toastEndTime + EXTRA_DELAY_AFTER_TOAST_MS
        val delay = (desiredTime - now).coerceAtLeast(0L)

        dialogHandler.postDelayed({ block() }, delay)
    }

}