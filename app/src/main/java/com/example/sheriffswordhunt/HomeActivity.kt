package com.example.sheriffswordhunt

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sheriffswordhunt.databinding.ActivityHomeBinding
import com.example.sheriffswordhunt.ui.casefiles.CaseFilesFragment
import com.example.sheriffswordhunt.ui.mission.MissionActivity
import com.example.sheriffswordhunt.ui.orders.SheriffsOrdersFragment

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.heroSection.imgHero.setImageResource(R.drawable.hero_home)

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