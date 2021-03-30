package com.sekar.repository

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.sekar.modal.Photo
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import java.util.concurrent.TimeUnit


private  val okHttpClient= OkHttpClient.Builder().connectTimeout(30, TimeUnit.MINUTES).readTimeout(30,
    TimeUnit.MINUTES).writeTimeout(30, TimeUnit.MINUTES).build()

private const val BaseURL="https://jsonplaceholder.typicode.com/"
private val moshi= Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
private val retrofit= Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
    .addCallAdapterFactory(CoroutineCallAdapterFactory()).client(okHttpClient).baseUrl(BaseURL).build()


interface ApiService {
    @Headers("Content-Type:application/json")
    @GET("photos/")
    fun getPhotos(
    ): Deferred<List<Photo>>

    object AppApi{
        val retrofitService:ApiService by lazy {
            retrofit.create(ApiService::class.java)

        }
    }
}