package com.vivadevs.vikipedia.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vivadevs.vikipedia.databinding.MeaningRecycleViewBinding
import com.vivadevs.vikipedia.model.Meaning

class MeaningAdapter(private var meaningList: List<Meaning>): RecyclerView.Adapter<MeaningAdapter.MeaningViewHolder>() {

    class MeaningViewHolder(
        private val binding: MeaningRecycleViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(meaning: Meaning) {
            // BIND ALL THE MEANINGS
            binding.partOfSpeechTextview.text = meaning.partOfSpeech
            binding.definitionsTextview.text = meaning.definitions.joinToString("\n\n") {
                var currentIndex = meaning.definitions.indexOf(it)
                (currentIndex+1).toString()+". "+it.definition.toString()
            }
            if(meaning.synonyms.isNotEmpty()) {
                binding.synonymsTitleTextview.visibility = View.GONE
                binding.synonymsTextview.visibility = View.GONE
            } else {
                binding.synonymsTitleTextview.visibility = View.VISIBLE
                binding.synonymsTextview.visibility = View.VISIBLE
                binding.synonymsTextview.text = meaning.synonyms.joinToString(", ")
            }
            if(meaning.antonyms.isNotEmpty()) {
                binding.antonymsTitleTextview.visibility = View.GONE
                binding.antonymsTextview.visibility = View.GONE
            } else {
                binding.antonymsTitleTextview.visibility = View.VISIBLE
                binding.antonymsTextview.visibility = View.VISIBLE
                binding.antonymsTextview.text = meaning.antonyms.joinToString(", ")
            }
        }
    }

    fun updateNewData(newmeaningList: List<Meaning>) {
        this.meaningList = newmeaningList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeaningViewHolder {
        val binding = MeaningRecycleViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MeaningViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return meaningList.size
    }

    override fun onBindViewHolder(holder: MeaningViewHolder, position: Int) {
        holder.bind(meaningList[position])
    }
}