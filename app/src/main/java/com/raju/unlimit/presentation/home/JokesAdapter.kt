package com.raju.unlimit.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.raju.unlimit.databinding.JokesItemBinding
import com.raju.unlimit.domain.model.JokesDetail

class JokesAdapter(
    var jokesList: List<JokesDetail>
) : RecyclerView.Adapter<JokesAdapter.ViewHolder>() {
    private lateinit var binding: JokesItemBinding

    inner class ViewHolder(binding: JokesItemBinding) : RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = JokesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val joke = jokesList[position]
        binding.tvJokes.text = joke.joke
        binding.rlContainer.setOnClickListener {
            Toast.makeText(binding.rlContainer.context, "${joke.joke}", Toast.LENGTH_LONG).show()
        }
    }

    override fun getItemCount(): Int {
        return jokesList.size
    }
}
