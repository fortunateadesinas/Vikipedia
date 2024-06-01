package com.vivadevs.vikipedia

import android.app.Application
import androidx.room.Room
import com.vivadevs.vikipedia.db.NoteDatabase

class MainApplication: Application() {
    companion object {
        lateinit var noteDatabase: NoteDatabase
    }
    override fun onCreate() {
        super.onCreate()
        noteDatabase = Room.databaseBuilder(
            applicationContext,
            NoteDatabase::class.java,
            NoteDatabase.DB_NAME
        ).build()
    }
}