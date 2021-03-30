
package com.sekar.ui.photoView

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.sekar.R
import com.sekar.adapter.PhotoRecyclerViewAdapter
import com.sekar.databinding.ActivityPhotoViewBinding
import com.sekar.modal.Photo
import com.sekar.ui.fav.FavPhotoActivity
import com.sekar.ui.photoDetailView.PhotoDetailActivity


class PhotoViewActivity : AppCompatActivity() {

    private lateinit var viewModel: PhotoViewModel
    lateinit var mBinding:ActivityPhotoViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PhotoViewModel::class.java)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_photo_view)
        mBinding.viewModel = viewModel
        subscribeToLiveData()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.itemId
        if (id == R.id.myFav) {
            startActivity(Intent(this,FavPhotoActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }


    private fun subscribeToLiveData(){
        viewModel.response.observe(this, {
            if (it == "success") {
                mBinding.progressBar.visibility = View.GONE
                mBinding.photoRecyclerView.adapter = PhotoRecyclerViewAdapter(
                    viewModel.photos,
                    object : PhotoRecyclerViewAdapter.PhotoClick {
                        override fun onPhotoClick(photo: Photo) {
                            var i = Intent(this@PhotoViewActivity, PhotoDetailActivity::class.java)
                            i.putExtra("photo", photo)
                            startActivity(i)
                        }
                    })
            }
        })
    }

}