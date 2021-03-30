package com.sekar.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.sekar.R
import com.sekar.modal.Photo
import com.squareup.picasso.Picasso


class PhotoRecyclerViewAdapter(val mPhotos: List<Photo>, val photoClick:PhotoClick) :RecyclerView.Adapter<PhotoRecyclerViewAdapter.ViewHolder>(){

    class ViewHolder(photoView: View) :RecyclerView.ViewHolder(photoView){
        val imageView = photoView.findViewById<ImageView>(R.id.photo)
    }

    interface PhotoClick{
        fun onPhotoClick(photo:Photo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val photoView = LayoutInflater.from(parent.context).inflate(R.layout.photo_item, parent, false)
        return ViewHolder(photoView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imageView = holder.imageView
        val photo = mPhotos[position]

        var url = photo.thumbnailUrl

        url = url.substring(0,4)+url.substring(5,url.length-1)

        Picasso.get().load(url).into(imageView)

        imageView.setOnClickListener {
            photoClick.onPhotoClick(photo)
        }


    }

    override fun getItemCount(): Int {
        return mPhotos.size
    }

}
