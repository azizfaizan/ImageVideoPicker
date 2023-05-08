package com.example.picikmagevideo

import android.annotation.SuppressLint
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class ImageAdapter: RecyclerView.Adapter<ImageAdapter.MyViewHolder>() {
    private var imageList= mutableListOf<Uri>()
    class MyViewHolder(view:View):RecyclerView.ViewHolder(view) {

        val image: ImageView =view.findViewById(R.id.image_view)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.imageview,parent,false)
        return MyViewHolder(view)

    }
    @SuppressLint("NotifyDataSetChanged")
     fun addData(list: List<Uri>){
        imageList.addAll(list)
        notifyDataSetChanged()

    }

    override fun getItemCount(): Int {
        return imageList.size

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val list=imageList[position]
        holder.image.setImageURI(list)
    }
}