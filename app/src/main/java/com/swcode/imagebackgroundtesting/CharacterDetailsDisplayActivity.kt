package com.swcode.imagebackgroundtesting

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.swcode.imagebackgroundtesting.databinding.ActivityCharacterDetailsDisplayBinding

class CharacterDetailsDisplayActivity: AppCompatActivity() {

    private var binding: ActivityCharacterDetailsDisplayBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharacterDetailsDisplayBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val name = intent.getStringExtra("name")
        val race = intent.getStringExtra("race")
        val charClass = intent.getStringExtra("charClass")
        val gender = intent.getStringExtra("gender")
        val age = intent.getStringExtra("age")
        val appearance = intent.getStringExtra("appearance")
        val history = intent.getStringExtra("history")
        val equipment = intent.getStringExtra("equipment")
        val alignment = intent.getStringExtra("alignment")

        Log.i("charAge from intent", age.toString())
        binding?.tvName?.text = name
        binding?.tvCharRace?.text = race
        binding?.tvClass?.text = charClass
        binding?.tvCharAge?.text = age
        binding?.tvCharGender?.text = gender
        binding?.tvCharAppearance?.text = appearance
        binding?.tvCharHistory?.text = history
        binding?.tvCharEquipment?.text = equipment
        binding?.tvCharAlignment?.text = alignment


    }

}