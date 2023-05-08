package com.example.picikmagevideo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.picikmagevideo.databinding.ActivityFormBinding
import com.example.picikmagevideo.databinding.ActivityMainBinding
import com.example.picikmagevideo.databinding.ActivityVideoBinding

class FormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFormBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityFormBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.toolbar.title="Form"
        binding.submit.setOnClickListener{
            Toast.makeText(this,"Validation check",Toast.LENGTH_LONG).show()
        }
    }
}