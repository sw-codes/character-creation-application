package com.swcode.imagebackgroundtesting

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "character-table")
data class CharacterEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val characterName: String = "",
    val characterGender: String = "",
    val characterRace: String = "",
    val characterClass: String = "",
    val characterAge: String = "",
    val characterAppearance: String = "",
    val characterHistory: String = "",
    val characterAlignment: String = "",
    val characterEquipment: String = "",
)
