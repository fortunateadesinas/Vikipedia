package com.vivadevs.vikipedia.activity


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.vivadevs.vikipedia.R
import com.vivadevs.vikipedia.screen.NoteListScreen
import com.vivadevs.vikipedia.viewmodel.NoteViewModel


class NoteListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list) // Ensure this matches your XML file name
        val noteViewModel = ViewModelProvider(this)[NoteViewModel::class.java]
        val composeView = findViewById<ComposeView>(R.id.my_composable)
        composeView.setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = colorResource(R.color.my_primary)
                ) {
                    val navController = rememberNavController()
                    NoteListScreen(noteViewModel, navController)
                }
            }
        }
    }
}