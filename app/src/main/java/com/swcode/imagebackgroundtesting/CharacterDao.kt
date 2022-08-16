package com.swcode.imagebackgroundtesting

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    @Insert
    suspend fun insert (characterEntity: CharacterEntity)

    @Update
    suspend fun update (characterEntity: CharacterEntity)

    @Delete
    suspend fun delete(characterEntity: CharacterEntity)

    @Query("SELECT * FROM `character-table`")
    fun fetchAllCharacters(): Flow<List<CharacterEntity>>

    @Query("SELECT * FROM `character-table` where id = :id")
    fun fetchCharacterById(id: Int): Flow<List<CharacterEntity>>
}