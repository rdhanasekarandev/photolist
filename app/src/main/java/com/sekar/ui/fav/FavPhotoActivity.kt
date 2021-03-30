package com.sekar.ui.fav

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.sekar.R
import com.sekar.adapter.PhotoRecyclerViewAdapter
import com.sekar.database.FavPhotoDatabase
import com.sekar.databinding.ActivityFavPhotoBinding
import com.sekar.databinding.ActivityPhotoDetailBinding
import com.sekar.databinding.ActivityPhotoViewBinding
import com.sekar.entity.FavPhoto
import com.sekar.modal.Photo
import com.sekar.ui.photoDetailView.PhotoDetailActivity
import com.sekar.ui.photoDetailView.PhotoDetailViewModel
import com.sekar.ui.photoView.PhotoViewModel

class FavPhotoActivity : AppCompatActivity() {

    private lateinit var viewModel: FavPhotoViewModel
    lateinit var mBinding: ActivityFavPhotoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FavPhotoViewModel::class.java)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_fav_photo)
        mBinding.viewModel = viewModel
        showFav()
    }

    private fun showFav(){
        val db = Room.databaseBuilder(
            applicationContext,
            FavPhotoDatabase::class.java, "database-name"
        ).allowMainThreadQueries().build()
        var photoList = arrayListOf<Photo>()
        var favPhotoList = db.photoDao().getAll()

        if(favPhotoList.isNotEmpty()) {

            favPhotoList.forEach {
                var photo =
                    Photo(it.albumId!!, it.uid.toString()!!, it.title!!, it.url!!, it.thumbnail!!)
                photoList.add(photo)
            }

            mBinding.progressBar.visibility = View.GONE
            mBinding.photoRecyclerView.adapter = PhotoRecyclerViewAdapter(
                photoList,
                object : PhotoRecyclerViewAdapter.PhotoClick {
                    override fun onPhotoClick(photo: Photo) {
                    }
                })
        }else{
            mBinding.progressBar.visibility=View.GONE
            Toast.makeText(this,"There is no Favourite Item",Toast.LENGTH_LONG).show()
        }

    }
}