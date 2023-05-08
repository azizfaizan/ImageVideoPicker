package com.example.picikmagevideo

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.picikmagevideo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val imageAdapter: ImageAdapter by lazy {
        ImageAdapter()
    }
    private lateinit var binding: ActivityMainBinding
    private lateinit var pickImage: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = imageAdapter
        binding.toolbar.toolbar.title = "Images"
        pickImageFormGallery()
        binding.pickImage.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            intent.action = Intent.ACTION_GET_CONTENT
            pickImage.launch(intent)
        }
        binding.nextPage.setOnClickListener {
            startActivity(Intent(this@MainActivity, VideoActivity::class.java))
        }

    }

    private fun pickImageFormGallery() {
        pickImage =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                val imageUris = mutableListOf<Uri>()
                if (result.resultCode == Activity.RESULT_OK) {

                    val data: Intent? = result.data
                    val clipData = data?.clipData
                    if (clipData != null) {

                        for (i in 0 until clipData.itemCount) {
                            imageUris.add(clipData.getItemAt(i).uri)

                        }
                    } else {

                        data?.data?.let { imageUris.add(it) }

                    }
                    imageAdapter.addData(imageUris)
                    binding.recyclerView.adapter = imageAdapter
                }
            }
    }


}