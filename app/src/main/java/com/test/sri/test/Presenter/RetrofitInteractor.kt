package com.test.sri.test.Presenter

import com.test.sri.test.Interface.MainContract
import com.test.sri.test.Model.FlickrDataModel
import com.test.sri.test.Interface.RetrofitApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response





/**
 * Created by Myworld on 28/04/2018.
 */
class RetrofitInteractor(var mainview: MainContract.MainView) : MainContract.MainPresenter {
    //Initialising variables
    lateinit var retrofitApiService: RetrofitApiService
    lateinit var data: FlickrDataModel

    //This method will make a call to Flickr API and will retrieve Json data
    override fun getDataFromServer() {

        retrofitApiService = RetrofitApiService.create()
        val call = retrofitApiService.loadfeed()
        call.enqueue(object : Callback<FlickrDataModel> {
            override fun onFailure(call: Call<FlickrDataModel>?, t: Throwable?) {
            }
            override fun onResponse(call: Call<FlickrDataModel>?, response: Response<FlickrDataModel>?) {
                data = response!!.body()!!
                if (data != null ) {
                    //This will call the view method to display data on the relevant layout
                    mainview.imageList(data)
                }
            }
        })


    }
}