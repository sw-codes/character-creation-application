package com.swcode.imagebackgroundtesting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.swcode.imagebackgroundtesting.databinding.ActivityCreateGw2Binding
import kotlinx.coroutines.launch

class CreateGW2Activity : AppCompatActivity() {
    var binding: ActivityCreateGw2Binding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateGw2Binding.inflate(layoutInflater)
        setContentView(binding?.root)

        val characterDao = (application as CharacterApp).db.characterDao()

        binding?.btnCreateGW2Char?.setOnClickListener {
            addRecord(characterDao)
        }

        var gw2RaceAdapter = ArrayAdapter<String>(this,
            android.R.layout.simple_list_item_1,
            resources.getStringArray(R.array.GW2Races))
        gw2RaceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding?.spinnerGW2Races?.adapter = gw2RaceAdapter

        var gw2ClassAdapter = ArrayAdapter<String>(this,
            android.R.layout.simple_list_item_1,
            resources.getStringArray(R.array.GW2Classes))
        gw2ClassAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding?.spinnerGW2Classes?.adapter = gw2ClassAdapter
    }

    private fun addRecord(characterDao: CharacterDao) {
        val name = binding?.etGW2CharName?.text.toString()
        val gender = binding?.etGW2CharGender?.text.toString()
        //val race = binding?.etWarcraftCharRace?.text.toString()
        val race = binding?.spinnerGW2Races?.selectedItem.toString()
        val characterClass = binding?.spinnerGW2Classes?.selectedItem.toString()
        val characterAppearance = binding?.etGW2CharAppearance?.text.toString()
        val characterHistory = binding?.etGW2CharHistory?.text.toString()

        if(name.isNotEmpty() && race.isNotEmpty() && characterClass.isNotEmpty()
            && gender.isNotEmpty()) {
            lifecycleScope.launch {
                characterDao.insert(CharacterEntity(characterName = name, characterGender = gender,
                    characterRace = race, characterClass = characterClass, characterAppearance = characterAppearance))
                Toast.makeText(applicationContext, "character saved", Toast.LENGTH_SHORT).show()

                binding?.etGW2CharName?.text?.clear()
                binding?.etGW2CharGender?.text?.clear()
                //binding?.etWarcraftCharRace?.text?.clear()
                //binding?.etWarcraftCharClass?.text?.clear()
                binding?.etGW2CharAppearance?.text?.clear()
                binding?.etGW2CharHistory?.text?.clear()
            }
        } else {
            Toast.makeText(applicationContext, "invalid data", Toast.LENGTH_SHORT).show()
        }

        val intent = Intent(this, CharacterDetailsDisplayActivity::class.java)
        intent.putExtra("name", name)
        intent.putExtra("gender", gender)
        intent.putExtra("race", race)
        intent.putExtra("charClass", characterClass)
        intent.putExtra("charAppearance", characterAppearance)
        intent.putExtra("charHistory", characterHistory)
        startActivity(intent)
        finish()
    }
}