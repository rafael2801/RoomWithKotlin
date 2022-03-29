package com.example.room.repository

import androidx.annotation.WorkerThread
import com.example.room.database.daos.WordDao
import com.example.room.database.models.Word
import kotlinx.coroutines.flow.Flow

class WordRepository (private val wordDao: WordDao) {

    val allWords: Flow<List<Word>> = wordDao.getAlphabetizeWords()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert (word: Word){
        wordDao.insert(word)
    }
}