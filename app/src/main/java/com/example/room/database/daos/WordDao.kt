package com.example.room.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.room.database.models.Word
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Word)

    @Query("SELECT * FROM word_table ORDER BY word ASC")
    fun getAlphabetizeWords(): Flow<List<Word>>

    @Query("DELETE FROM word_table")
    suspend fun deleteAll()

}