package com.swcode.imagebackgroundtesting

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import com.swcode.imagebackgroundtesting.databinding.ActivityCreateCustomBinding
import kotlinx.coroutines.launch

class CreateCustomActivity : AppCompatActivity() {
    var binding: ActivityCreateCustomBinding? = null

    val openGalleryLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
                result ->
            if (result.resultCode == RESULT_OK && result.data != null) {
                binding?.ivCharImageOne?.setImageURI(result.data?.data)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateCustomBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val characterDao = (application as CharacterApp).db.characterDao()

        binding?.btnCustomCreate?.setOnClickListener {
            addRecord(characterDao)
        }
        binding?.ivCharImageOne?.setOnClickListener {
            val pickIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            openGalleryLauncher.launch(pickIntent)
        }
    }

    private fun addRecord(characterDao: CharacterDao) {
        val name = binding?.etCharName?.text.toString()
        val gender = binding?.etCharGender?.text.toString()
        val race = binding?.etCharRace?.text.toString()
        val characterClass = binding?.etCharClass?.text.toString()
        val age = binding?.etCharAge?.text.toString()
        val appearance = binding?.etCharAppearance?.text.toString()
        val history = binding?.etCharHistory?.text.toString()
        val equipment = binding?.etCharEquipment?.text.toString()
        val alignment = binding?.etCharAlignment?.text.toString()

        if(name.isNotEmpty() && race.isNotEmpty() && characterClass.isNotEmpty()
            && gender.isNotEmpty() && age.isNotEmpty() && appearance.isNotEmpty() && history.isNotEmpty()
            && equipment.isNotEmpty() && alignment.isNotEmpty()) {
            lifecycleScope.launch {
                characterDao.insert(CharacterEntity(characterName = name, characterGender = gender,
                    characterRace = race, characterClass = characterClass, characterAge = age,
                characterHistory = history, characterAppearance = appearance, characterAlignment = alignment,
                characterEquipment = equipment))
                Toast.makeText(applicationContext, "character saved", Toast.LENGTH_SHORT).show()
                binding?.etCharName?.text?.clear()
                binding?.etCharGender?.text?.clear()
                binding?.etCharRace?.text?.clear()
                binding?.etCharClass?.text?.clear()
                binding?.etCharAge?.text?.clear()
                binding?.etCharAppearance?.text?.clear()
                binding?.etCharHistory?.text?.clear()
                binding?.etCharEquipment?.text?.clear()
                binding?.etCharAlignment?.text?.clear()
            }
        } else {
            Toast.makeText(applicationContext, "invalid data", Toast.LENGTH_SHORT).show()
        }

        val intent = Intent(this, CharacterDetailsDisplayActivity::class.java)
        intent.putExtra("name", name)
        intent.putExtra("gender", gender)
        intent.putExtra("race", race)
        intent.putExtra("charClass", characterClass)
        intent.putExtra("age", age)
        intent.putExtra("appearance", appearance)
        intent.putExtra("history", history)
        intent.putExtra("equipment", equipment)
        intent.putExtra("alignment", alignment)
        startActivity(intent)

        finish()
    }
}