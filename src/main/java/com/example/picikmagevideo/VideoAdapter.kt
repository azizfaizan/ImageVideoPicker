package com.example.picikmagevideo

import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaMetadataRetriever
import android.media.ThumbnailUtils
import android.net.Uri
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class VideoAdapter : RecyclerView.Adapter<VideoAdapter.MyViewHolder>() {
    lateinit var context:Context
    private var videoList= mutableListOf<Uri>()
    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val videoThumbnail: ImageView =view.findViewById(R.id.image_view)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        context=parent.context
        val view= LayoutInflater.from(parent.context).inflate(R.layout.imageview,parent,false)
        return MyViewHolder(view)

    }
    @SuppressLint("NotifyDataSetChanged")
     fun addData(list: List<Uri>){
        videoList.addAll(list)
        notifyDataSetChanged()

    }

    override fun getItemCount(): Int {
        return videoList.size

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val list=videoList[position]
        val retriever = MediaMetadataRetriever()
        retriever.setDataSource(context, list)

        val bitmap = retriever.getFrameAtTime()
        holder.videoThumbnail.setImageBitmap(bitmap)

        retriever.release()
      /*  val thumbnail = list.path?.let { ThumbnailUtils.createVideoThumbnail(it, MediaStore.Video.Thumbnails.MINI_KIND) }
        holder.videoThumbnail.setImageBitmap(thumbnail)*/
    }
}