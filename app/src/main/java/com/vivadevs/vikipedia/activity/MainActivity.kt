package com.vivadevs.vikipedia.activity

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.vivadevs.vikipedia.adapter.MeaningAdapter
import com.vivadevs.vikipedia.databinding.ActivityMainBinding
import com.vivadevs.vikipedia.db.NoteDatabase
import com.vivadevs.vikipedia.model.WordResult
import com.vivadevs.vikipedia.retrofit.RetrofitInstance
import com.vivadevs.vikipedia.viewmodel.NoteViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: NoteViewModel

    companion object {
        lateinit var noteDatabase: NoteDatabase
    }

    lateinit var binding: ActivityMainBinding

    lateinit var adapter: MeaningAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize the ViewModel after onCreate() has been called
        viewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        // Call getMeaning() with the word "Welcome" when the app is loaded
        getMeaning("Welcome")

        binding.searchBtn.setOnClickListener {
            val word = binding.searchInput.text.toString()
            getMeaning(word)
            // Clear the search input field
            binding.searchInput.setText("")
        }

        adapter = MeaningAdapter(emptyList())
        binding.meaningRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.meaningRecyclerView.adapter = adapter

        binding.menuBtn.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)

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
    }

    private fun getMeaning(word: String) {
        setInProgress(true)
        GlobalScope.launch {
            try {
                val response = RetrofitInstance.dictionaryApi.getMeaning(word)
                if(response.body()==null){
                    throw (Exception())
                }
                runOnUiThread {
                    setInProgress(false)
                    response.body()?.first()?.let {
                        setUI(it)
                    }
                }
            }catch (e : Exception){
                runOnUiThread{
                    setInProgress(false)
                    Toast.makeText(applicationContext,"Something went wrong",Toast.LENGTH_SHORT).show()
                }
            }


        }
    }

    private fun setUI(response: WordResult) {
        binding.wordTextview.text = response.word
        binding.wordTextview2.text = response.word
        binding.phoneticTextview.text = response.phonetic
        adapter.updateNewData(response.meanings)
    }

    private fun setInProgress(inProgress: Boolean) {
        if (inProgress) {
            binding.searchBtn.visibility = View.INVISIBLE
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.searchBtn.visibility = View.VISIBLE
            binding.progressBar.visibility = View.INVISIBLE
        }
    }
}