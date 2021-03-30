package com.sekar.ui.photoDetailView

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.sekar.R
import com.sekar.database.FavPhotoDatabase
import com.sekar.databinding.ActivityPhotoDetailBinding
import com.sekar.databinding.ActivityPhotoViewBinding
import com.sekar.entity.FavPhoto
import com.sekar.modal.Photo
import com.sekar.ui.photoView.PhotoViewModel
import com.squareup.picasso.Picasso

class PhotoDetailActivity : AppCompatActivity() {
    private lateinit var viewModel: PhotoDetailViewModel
    lateinit var mBinding: ActivityPhotoDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PhotoDetailViewModel::class.java)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_photo_detail)
        mBinding.viewModel = viewModel

        var photo = intent.extras?.get("photo") as Photo

        mBinding.albumId.text = "Album Id: "+photo.albumId

        mBinding.id.text = "Id: "+photo.id

        mBinding.title.text = "Title: "+photo.title
        var url = photo.url
        url = url.substring(0,4)+url.substring(5,url.length-1)
        Picasso.get().load(url).placeholder(R.drawable.ic_launcher_background).into(mBinding.imageView)
        val db = Room.databaseBuilder(
            applicationContext,
            FavPhotoDatabase::class.java, "database-name"
        ).allowMainThreadQueries().build()
        mBinding.favButton.setOnClickListener {
            val dao = db.photoDao()
            val favphoto = FavPhoto(null,photo.albumId,photo.title,photo.thumbnailUrl,photo.url)

            dao.insert(favphoto)

            val allphoto=dao.getAll().toString()
            Log.d("photos",allphoto)
        }
    }
}