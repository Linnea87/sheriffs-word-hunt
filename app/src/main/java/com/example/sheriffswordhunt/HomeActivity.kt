package com.example.sheriffswordhunt

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sheriffswordhunt.databinding.ActivityHomeBinding
import com.example.sheriffswordhunt.ui.casefiles.CaseFilesFragment
import com.example.sheriffswordhunt.ui.mission.MissionActivity
import com.example.sheriffswordhunt.ui.orders.SheriffsOrdersFragment

// ========== UI: Home Activity ==========
// Entry screen for the game. Navigates to missions, Sheriff's Orders and Case Files.

class HomeActivity : AppCompatActivity() {

    // ========== BINDING ==========

    private lateinit var binding: ActivityHomeBinding

    // ========== LIFECYCLE ==========

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.heroSection.imgHero.setImageResource(R.drawable.hero_home)

        // ========== NAVIGATION BUTTONS ==========

        binding.btnStartMission.setOnClickListener {
            val intent = Intent(this, MissionActivity::class.java)
            startActivity(intent)
        }

        binding.btnOpenCaseFiles.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main, CaseFilesFragment())
                .addToBackStack(null)
                .commit()
        }

        binding.btnOpenOrders.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main, SheriffsOrdersFragment())
                .addToBackStack(null)
                .commit()
        }
    }
}