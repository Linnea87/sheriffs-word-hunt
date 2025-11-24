package com.example.sheriffswordhunt

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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