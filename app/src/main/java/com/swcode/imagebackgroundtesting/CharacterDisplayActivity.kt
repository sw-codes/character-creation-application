package com.swcode.imagebackgroundtesting

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.swcode.imagebackgroundtesting.databinding.ActivityCharacterDisplayBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CharacterDisplayActivity : AppCompatActivity(), OnCharacterClickListener {

    private var binding: ActivityCharacterDisplayBinding? = null
    private var chars = ArrayList<CharacterEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharacterDisplayBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val characterDao = (application as CharacterApp).db.characterDao()

        lifecycleScope.launch {
            characterDao.fetchAllCharacters().collect {
                val list = ArrayList(it)
                chars = list
                setupListOfDataIntoRecyclerView(list, characterDao)
            }
        }
    }

    private fun setupListOfDataIntoRecyclerView(charactersList: ArrayList<CharacterEntity>, characterDao: CharacterDao) {
        if (charactersList.isNotEmpty()) {
            val itemAdapter = ItemAdapter(charactersList,
                {
                    deleteId->
                    deleteRecordAlertDialog(deleteId, characterDao)
                },
                this
            )
            binding?.rvCharacterList?.layoutManager = LinearLayoutManager(this)
            binding?.rvCharacterList?.adapter = itemAdapter
        }
    }

    private fun deleteRecordAlertDialog(id: Int, characterDao: CharacterDao) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Delete character?")

        builder.setPositiveButton("Yes") { dialogInterface, _ ->
            lifecycleScope.launch {
                characterDao.delete(CharacterEntity(id))
                Toast.makeText(applicationContext, "Character has been deleted.", Toast.LENGTH_SHORT).show()
                binding?.rvCharacterList?.adapter?.notifyItemRemoved(id)
            }
            dialogInterface.dismiss()
        }

        builder.setNegativeButton("No") { dialogInterface, which ->
            dialogInterface.dismiss()
        }

        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

    override fun onCharacterItemClicked(position: Int) {
        val intent = Intent(this, CharacterDetailsDisplayActivity::class.java)
        intent.putExtra("name", chars[position].characterName)
        intent.putExtra("gender", chars[position].characterGender)
        intent.putExtra("race", chars[position].characterRace)
        intent.putExtra("charClass", chars[position].characterClass)
        intent.putExtra("age", chars[position].characterAge)
        startActivity(intent)
    }
}