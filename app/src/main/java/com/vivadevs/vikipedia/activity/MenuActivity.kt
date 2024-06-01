package com.vivadevs.vikipedia.activity

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.vivadevs.vikipedia.R

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val closeNav = findViewById<ImageView>(R.id.closeNav)
        closeNav.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)

            // Create a custom animation that slides the new screen in from the left
            val animation = ObjectAnimator.ofFloat(
                window.decorView,
                "translationX",
                window.decorView.width.toFloat(),
                0f
            )
            animation.duration = 5000

            // Start the animation and then start the activity
            animation.start()
            startActivity(intent)
        }

        val notePad = findViewById<LinearLayout>(R.id.notepadNav)
        notePad.setOnClickListener {
            val intent = Intent(this, NotePadActivity::class.java)
            startActivity(intent)
        }

        val randomQuote = findViewById<LinearLayout>(R.id.randomQuoteNav)
        randomQuote.setOnClickListener {
            val intent = Intent(this, QuoteActivity::class.java)
            startActivity(intent)
        }

        val todayPlan = findViewById<LinearLayout>(R.id.todayPlanNav)
        todayPlan.setOnClickListener {
            val intent = Intent(this, NoteListActivity::class.java)
            startActivity(intent)
        }
    }
}