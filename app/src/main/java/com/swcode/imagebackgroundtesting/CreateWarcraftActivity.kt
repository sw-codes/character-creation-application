package com.swcode.imagebackgroundtesting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.swcode.imagebackgroundtesting.databinding.ActivityCreateWarcraftBinding
import kotlinx.coroutines.launch

class CreateWarcraftActivity : AppCompatActivity() {

    var binding: ActivityCreateWarcraftBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateWarcraftBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val characterDao = (application as CharacterApp).db.characterDao()

        binding?.btnCreateWarcraftChar?.setOnClickListener {
            addRecord(characterDao)
        }

        var warcraftRaceAdapter = ArrayAdapter<String>(this,
            android.R.layout.simple_list_item_1,
            resources.getStringArray(R.array.warcraftRaces))
        warcraftRaceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        binding?.spinnerWarcraftRaces?.adapter = warcraftRaceAdapter

        var warcraftClassAdapter = ArrayAdapter<String>(this,
            android.R.layout.simple_list_item_1,
            resources.getStringArray(R.array.warcraftClasses))
        warcraftRaceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding?.spinnerWarcraftClasses?.adapter = warcraftClassAdapter


    }

    private fun addRecord(characterDao: CharacterDao) {
        val name = binding?.etWarcraftCharName?.text.toString()
        val gender = binding?.etWarcraftCharGender?.text.toString()
        val race = binding?.spinnerWarcraftRaces?.selectedItem.toString()
        val characterClass = binding?.spinnerWarcraftClasses?.selectedItem.toString()
        val characterAppearance = binding?.etWarcraftCharAppearance?.text.toString()

        if(name.isNotEmpty() && race.isNotEmpty() && characterClass.isNotEmpty()
            && gender.isNotEmpty()) {
            lifecycleScope.launch {
                characterDao.insert(CharacterEntity(characterName = name, characterGender = gender,
                    characterRace = race, characterClass = characterClass, characterAppearance = characterAppearance))
                Toast.makeText(applicationContext, "character saved", Toast.LENGTH_SHORT).show()

                binding?.etWarcraftCharName?.text?.clear()
                binding?.etWarcraftCharGender?.text?.clear()
                binding?.etWarcraftCharAppearance?.text?.clear()
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
        startActivity(intent)
        finish()
    }
}