package com.example.room.application

import android.app.Application
import com.example.room.database.WordRoomDatabase
import com.example.room.repository.WordRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class WordsApplication: Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    val db by lazy { WordRoomDatabase.getDatabase(applicationScope, this) }
    val rep by lazy { WordRepository(db.wordDao()) }
}