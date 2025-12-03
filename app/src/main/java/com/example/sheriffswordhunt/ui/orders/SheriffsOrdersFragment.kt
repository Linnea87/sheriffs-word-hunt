package com.example.sheriffswordhunt.ui.orders

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sheriffswordhunt.databinding.FragmentSheriffsOrdersBinding

// ========== UI: Sheriff's Orders =========================================
// Displays the sheriff's instructions and provides navigation back to Home.

class SheriffsOrdersFragment : Fragment() {

    // ========== BINDING =============================================

    private var _binding: FragmentSheriffsOrdersBinding? = null
    private val binding get() = _binding!!

    // ========== LIFECYCLE =============================================

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSheriffsOrdersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ========== BUTTON LISTENERS =============================================

        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}