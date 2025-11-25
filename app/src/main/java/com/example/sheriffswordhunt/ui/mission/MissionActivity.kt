package com.example.sheriffswordhunt.ui.mission

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sheriffswordhunt.R
import com.example.sheriffswordhunt.databinding.ActivityMissionBinding

class MissionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMissionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMissionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.heroSection.imgHero.setImageResource(R.drawable.hero_mission)
    }
}