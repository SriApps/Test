package com.test.sri.test.Interface

import com.test.sri.test.Model.FlickrDataModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * Created by Myworld on 21/04/2018.
 */


interface RetrofitApiService {
    @GET("feeds/photos_public.gne?format=json&nojsoncallback=1")
    fun loadfeed(): Call<FlickrDataModel>;
    companion object RetrofitApiclient {
        fun create(): RetrofitApiService {
            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://api.flickr.com/services/")
                    .build()
            return retrofit.create<RetrofitApiService>(RetrofitApiService::class.java)
        }
    }
}