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

class CaseFilesFragment : Fragment() {

    private var _binding: FragmentCaseFilesBinding? = null
    private val binding get() = _binding!!

    private lateinit var gameProgressRepository: GameProgressRepository

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

        binding.tvCase1Title.setOnClickListener {
            openCase(1)
        }

        binding.tvCase2Title.setOnClickListener {
            openCase(2)
        }

        binding.tvCase3Title.setOnClickListener {
            openCase(3)
        }
        updateCaseStates()
    }

    private fun updateCaseStates() {
        val case1Unlocked = gameProgressRepository.isCaseUnlocked(1)
        val case2Unlocked = gameProgressRepository.isCaseUnlocked(2)
        val case3Unlocked = gameProgressRepository.isCaseUnlocked(3)

        setupCaseItem(
            titleView = binding.tvCase1Title,
            starView = binding.imgCase1Star,
            unlocked = case1Unlocked
        )

        setupCaseItem(
            titleView = binding.tvCase2Title,
            starView = binding.imgCase2Star,
            unlocked = case2Unlocked 
        )

        setupCaseItem(
            titleView = binding.tvCase3Title,
            starView = binding.imgCase3Star,
            unlocked = case3Unlocked
        )
    }

    private fun setupCaseItem(
        titleView: TextView,
        starView: ImageView,
        unlocked: Boolean
    ) {
        titleView.isEnabled = unlocked
        starView.isEnabled = unlocked

        val alpha = if (unlocked) 1f else 0.3f
        titleView.alpha = alpha
        starView.alpha = alpha

    }

    private fun openCase(caseId: Int) {
        val intent = Intent(requireContext(), MissionActivity::class.java).apply {
            putExtra(MissionActivity.EXTRA_CASE_ID, caseId)
        }
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}