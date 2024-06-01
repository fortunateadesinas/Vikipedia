package com.vivadevs.vikipedia.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [Note::class], version = 1)
@TypeConverters(DateTypeConverters::class)
abstract class NoteDatabase: RoomDatabase() {

    companion object {
        const val DB_NAME = "todo_pad.db"
    }
    abstract fun getNotesDao(): NoteDao
}