package com.test.sri.test.Interface

import com.test.sri.test.Model.FlickrDataModel

/**
 * Created by Myworld on 27/04/2018.
 */
interface MainContract {

    // Interface for the presenter
    interface MainPresenter{
        fun getDataFromServer()
    }
    //// Interface for the View
    interface MainView{
        fun imageList(imageData: FlickrDataModel)
    }
}