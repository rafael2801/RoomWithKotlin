package com.example.room

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.room.adapter.WordListAdapter
import com.example.room.application.WordsApplication
import com.example.room.database.models.Word
import com.example.room.databinding.ActivityMainBinding
import com.example.room.ui.viewModels.WordViewModel
import com.example.room.ui.viewModels.WordViewModelFactory
import com.ocanha.roomdatabasemvvm.ui.view.NewWord

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: WordListAdapter
    private lateinit var binding: ActivityMainBinding
//    private lateinit var resultLauncher: ActivityResultContracts

    private val wordViewModel: WordViewModel by viewModels{
        WordViewModelFactory((application as WordsApplication).rep)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        recyclerView = binding.recyclerview
        adapter = WordListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.let{
                    it.getStringExtra("REPLY")?.let { it1 -> Word(it1)}?.let { it2 -> wordViewModel.insert( it2 ) }
                }
            }
        }

        binding.fab.setOnClickListener {
            resultLauncher.launch(Intent(this, NewWord::class.java))
        }


    }

    override fun onStart() {
        super.onStart()
        wordViewModel.allWords.observe(this, Observer {
            it.let { adapter.submitList(it) }
        })
    }
}