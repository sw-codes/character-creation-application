package com.swcode.imagebackgroundtesting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.swcode.imagebackgroundtesting.databinding.ActivityChoosePresetBinding

class ChoosePresetActivity : AppCompatActivity() {
    var binding: ActivityChoosePresetBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChoosePresetBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.btnCreateWarcraft?.setOnClickListener {
            startActivity(Intent(this, CreateWarcraftActivity::class.java))
        }

        binding?.btnCreateGW2?.setOnClickListener {
            startActivity(Intent(this, CreateGW2Activity::class.java))
        }

        binding?.btnCreateLOTRO?.setOnClickListener {
            startActivity(Intent(this, CreateLOTROActivity::class.java))
        }

        binding?.btnCreateESO?.setOnClickListener {
            startActivity(Intent(this, CreateESOActivity::class.java))
        }

        binding?.btnCreateFF?.setOnClickListener {
            startActivity(Intent(this, CreateESOActivity::class.java))
        }

        binding?.btnCreateStarWars?.setOnClickListener {
            startActivity(Intent(this, CreateESOActivity::class.java))
        }
    }
}