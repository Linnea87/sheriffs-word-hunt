package com.example.sheriffswordhunt.ui.casefiles

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.sheriffswordhunt.data.repository.GameProgressRepository
import com.example.sheriffswordhunt.data.repository.GameProgressRepositoryImpl
import com.example.sheriffswordhunt.databinding.FragmentCaseFilesBinding
import com.example.sheriffswordhunt.ui.mission.MissionActivity

// ========== UI: CASE FILES ==========
// Displays available cases and locks/unlocks them based on progress.

class CaseFilesFragment : Fragment() {

    // ========== BINDING ==========

    private var _binding: FragmentCaseFilesBinding? = null
    private val binding get() = _binding!!

    // ========== REPOSITORY ==========

    private lateinit var gameProgressRepository: GameProgressRepository

    // ========== LIFECYCLE ==========

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCaseFilesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val prefs = requireContext()
            .getSharedPreferences("game_progress", Context.MODE_PRIVATE)
        gameProgressRepository = GameProgressRepositoryImpl(prefs)

        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        binding.tvCase1Title.setOnClickListener { openCase(1) }
        binding.tvCase2Title.setOnClickListener { openCase(2) }
        binding.tvCase3Title.setOnClickListener { openCase(3) }

        updateCaseStates()
    }

    // ========== UI UPDATES ==========

    private fun updateCaseStates() {
        setupCaseItem(binding.tvCase1Title, binding.imgCase1Star, gameProgressRepository.isCaseUnlocked(1))
        setupCaseItem(binding.tvCase2Title, binding.imgCase2Star, gameProgressRepository.isCaseUnlocked(2))
        setupCaseItem(binding.tvCase3Title, binding.imgCase3Star, gameProgressRepository.isCaseUnlocked(3))
    }

    private fun setupCaseItem(titleView: TextView, starView: ImageView, unlocked: Boolean) {
        val alpha = if (unlocked) 1f else 0.3f
        titleView.isEnabled = unlocked
        starView.isEnabled = unlocked
        titleView.alpha = alpha
        starView.alpha = alpha
    }

    // ========== NAVIGATION ==========

    private fun openCase(caseId: Int) {
        startActivity(
            Intent(requireContext(), MissionActivity::class.java).apply {
                putExtra(MissionActivity.EXTRA_CASE_ID, caseId)
            }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}