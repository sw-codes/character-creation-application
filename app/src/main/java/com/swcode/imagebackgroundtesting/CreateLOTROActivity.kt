package com.swcode.imagebackgroundtesting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.swcode.imagebackgroundtesting.databinding.ActivityCreateLotroBinding
import kotlinx.coroutines.launch

class CreateLOTROActivity : AppCompatActivity() {
    
    var binding: ActivityCreateLotroBinding? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateLotroBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val characterDao = (application as CharacterApp).db.characterDao()

        binding?.btnCreateLotroChar?.setOnClickListener {
            addRecord(characterDao)
        }

        var lotroRaceAdapter = ArrayAdapter<String>(this,
            android.R.layout.simple_list_item_1,
            resources.getStringArray(R.array.LotroRaces))
        lotroRaceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        binding?.spinnerLotroRaces?.adapter = lotroRaceAdapter

        var LotroClassAdapter = ArrayAdapter<String>(this,
            android.R.layout.simple_list_item_1,
            resources.getStringArray(R.array.LotroClasses))
        lotroRaceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding?.spinnerLotroClasses?.adapter = LotroClassAdapter
    }

    private fun addRecord(characterDao: CharacterDao) {
        val name = binding?.etLotroCharName?.text.toString()
        val gender = binding?.etLotroCharGender?.text.toString()
        //val race = binding?.etLotroCharRace?.text.toString()
        val race = binding?.spinnerLotroRaces?.selectedItem.toString()
        val characterClass = binding?.spinnerLotroClasses?.selectedItem.toString()
        val characterAppearance = binding?.etLotroCharAppearance?.text.toString()
        val charHistory = binding?.etLotroCharHistory?.text.toString()

        if(name.isNotEmpty() && race.isNotEmpty() && characterClass.isNotEmpty()
            && gender.isNotEmpty()) {
            lifecycleScope.launch {
                characterDao.insert(CharacterEntity(characterName = name, characterGender = gender,
                    characterRace = race, characterClass = characterClass, characterAppearance = characterAppearance,
                    characterHistory = charHistory))
                Toast.makeText(applicationContext, "character saved", Toast.LENGTH_SHORT).show()

                binding?.etLotroCharName?.text?.clear()
                binding?.etLotroCharGender?.text?.clear()
                //binding?.etLotroCharRace?.text?.clear()
                //binding?.etLotroCharClass?.text?.clear()
                binding?.etLotroCharAppearance?.text?.clear()
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
        intent.putExtra("charHistory", charHistory)
        startActivity(intent)
        finish()
    }
}