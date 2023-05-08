package com.example.picikmagevideo

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.picikmagevideo.databinding.ActivityVideoBinding

class VideoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVideoBinding
    private val videoAdapter:VideoAdapter by lazy {
        VideoAdapter()
    }
    private lateinit var pickVideo: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.toolbar.title="Videos"
        binding.recyclerViewVideo.layoutManager= LinearLayoutManager(this)
        binding.recyclerViewVideo.adapter=videoAdapter
        pickVideoFromGallery()
        binding.pickVideo.setOnClickListener {
            val intent = Intent().apply {
                type="video/*"
                action=Intent.ACTION_GET_CONTENT
            }
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            pickVideo.launch(intent)
        }
        binding.next.setOnClickListener{
            startActivity(Intent(this,FormActivity::class.java))
        }

    }

    private fun pickVideoFromGallery() {
        pickVideo=registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val videoUri = mutableListOf<Uri>()
            if (result.resultCode == Activity.RESULT_OK) {
                val clipData = result.data?.clipData
                if (clipData != null) {

                    for (i in 0 until clipData.itemCount) {
                        videoUri.add(clipData.getItemAt(i).uri)
                    }
                } else {
                   result.data?.data?.let { videoUri.add(it) }
                }
                videoAdapter.addData(videoUri)
                binding.recyclerViewVideo.adapter=videoAdapter
            }
        }
    }
}