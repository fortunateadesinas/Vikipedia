package com.vivadevs.vikipedia.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NoteDao {
    @Insert
    fun addNote(note: Note)
    @Query("SELECT * FROM Note ORDER BY createdAt DESC")
    fun getAllNotes(): LiveData<List<Note>>
    @Query("DELETE FROM Note WHERE id = :id")
    fun deleteNote(id: Int)
}