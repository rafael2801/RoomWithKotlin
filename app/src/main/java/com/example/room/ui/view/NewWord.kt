package com.ocanha.roomdatabasemvvm.ui.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import androidx.activity.viewModels
import com.example.room.application.WordsApplication
import com.example.room.databinding.ActivityNewWordBinding
import com.example.room.ui.viewModels.WordViewModel
import com.example.room.ui.viewModels.WordViewModelFactory

class NewWord : AppCompatActivity() {

    private lateinit var binding : ActivityNewWordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewWordBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        binding.buttonSave.setOnClickListener {

            val replyIntent = Intent()
            if(TextUtils.isEmpty(binding.editWord.text)){
                setResult(Activity.RESULT_CANCELED, replyIntent)
            }else{
                val word = binding.editWord.text.toString()
                replyIntent.putExtra("REPLY",word)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()

        }

    }
}