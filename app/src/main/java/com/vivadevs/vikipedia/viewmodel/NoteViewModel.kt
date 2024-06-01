package com.vivadevs.vikipedia.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vivadevs.vikipedia.MainApplication
import com.vivadevs.vikipedia.db.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.Date

class NoteViewModel : ViewModel() {
    val noteDao = MainApplication.noteDatabase.getNotesDao()
    val allNotes: LiveData<List<Note>> = noteDao.getAllNotes()

    @RequiresApi(Build.VERSION_CODES.O)
    fun addNote(content: String, title: String) {
        viewModelScope.launch(Dispatchers.IO)  {
            val note = Note(content = content, title = title, createdAt = Date.from(Instant.now()))
            noteDao.addNote(note)
        }
    }

    fun deleteNote(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            noteDao.deleteNote(id)
        }
    }
}