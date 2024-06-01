package com.vivadevs.vikipedia.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.vivadevs.vikipedia.R
import com.vivadevs.vikipedia.viewmodel.NoteViewModel

class NotePadActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_pad)

        val viewModel: NoteViewModel by viewModels()
        val saveNote = findViewById<ImageView>(R.id.saveNote)
        val backArrow = findViewById<ImageView>(R.id.backArrow)

        // Save the text to a file or database
        saveNote.setOnClickListener {
            val noteTextTitle = findViewById<EditText>(R.id.noteInputTitle).text.toString().trim()
            val noteTextContent = findViewById<EditText>(R.id.noteInputContent).text.toString().trim()
            if (noteTextContent.isEmpty() || noteTextTitle.isEmpty()) {
                // Handle empty note case (optional: show a toast)
                false
                Toast.makeText(applicationContext, "Title or Content cannot be empty", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.addNote(noteTextTitle, noteTextContent)
                val intent = Intent(this, NoteListActivity::class.java)
                startActivity(intent)
            }
        }

        backArrow.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
