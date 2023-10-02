package com.raju.unlimit.presentation.home
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.raju.unlimit.databinding.ActivityMainBinding
import com.raju.unlimit.domain.model.JokesDetail
import com.raju.unlimit.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private val viewModel: JokeViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private var jokesAdapter: JokesAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setViews()
        initObservers()
    }

    private fun initObservers() {
        viewModel.jokesLD.observe(this) {
            it.jokes.let { it1 ->
                binding.progressBar.visibility = View.GONE
                Log.d("Shivu",it1.toString())
                setupAdapter(it1)
            }
            if (it.error.isNotBlank()) {
                binding.progressBar.visibility = View.GONE
                Toast.makeText(this, it.error, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setupAdapter(it: List<JokesDetail>?) {
        if (jokesAdapter == null) {
            jokesAdapter = it?.let { it1 -> JokesAdapter(it1) }
            binding.rvJokes.adapter = jokesAdapter
        } else {
            jokesAdapter?.notifyDataSetChanged()
        }
    }

    private fun setViews() {
        binding.progressBar.visibility = View.VISIBLE
        binding.rvJokes.setHasFixedSize(true)
        binding.rvJokes.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvJokes.isNestedScrollingEnabled = true
    }

}