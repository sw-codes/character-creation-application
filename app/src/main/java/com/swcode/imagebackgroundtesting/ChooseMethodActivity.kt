package com.swcode.imagebackgroundtesting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.swcode.imagebackgroundtesting.databinding.ActivityChooseMethodBinding

class ChooseMethodActivity : AppCompatActivity() {

    var binding: ActivityChooseMethodBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChooseMethodBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.btnPresetOption?.setOnClickListener {
            startActivity(Intent(this, ChoosePresetActivity::class.java))
        }
        binding?.btnCustomOption?.setOnClickListener {
            startActivity(Intent(this, CreateCustomActivity::class.java))
        }
    }
}