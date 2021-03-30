package com.sekar.ui.photoView

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sekar.modal.Photo
import com.sekar.repository.ApiService
import kotlinx.coroutines.launch

class PhotoViewModel :ViewModel() {

    private val mResponse= MutableLiveData<String>()
    val response: LiveData<String>
        get() = mResponse

    var photos = listOf<Photo>()

    init {

        viewModelScope.launch {
            val getPhotoDetails = ApiService.AppApi.retrofitService.getPhotos()
            try {
                val details = getPhotoDetails.await()
                photos = details
                Log.d("details", details.toString())
                mResponse.value = "success"
            }catch (e:Exception){
                Log.d("details", e.message.toString())
                mResponse.value = "failed"
            }
        }
    }
}