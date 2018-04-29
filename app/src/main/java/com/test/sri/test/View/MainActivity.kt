package com.test.sri.test.View

import android.content.Context
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.test.sri.test.*
import com.test.sri.test.Adapter.MainViewPagerAdapter
import com.test.sri.test.Adapter.ThumbNailAdapter
import com.test.sri.test.Interface.MainContract
import com.test.sri.test.Model.FlickrDataModel
import com.test.sri.test.Model.Item
import com.test.sri.test.Presenter.RetrofitInteractor


class MainActivity : AppCompatActivity(), MainContract.MainView {
    //Initialising variables
    private lateinit var rvAdapter: RecyclerView.Adapter<*>
    private lateinit var mainViewPagerAdapter: MainViewPagerAdapter
    private lateinit var linearLayoutManager:LinearLayoutManager
    private var highlight=0
    private var lastPosition=0
    var data: FlickrDataModel? = null
    private lateinit var imageUrlList: List<Item>
    private lateinit var viewpager : ViewPager
    private lateinit var recyclerView:RecyclerView
    private var viewPagerPosition =0
    private lateinit var mainpresentor: MainContract.MainPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_main)
         viewpager=findViewById<ViewPager>(R.id.largeImage_VP)
         recyclerView= findViewById<RecyclerView>(R.id.recyclerView)
         mainpresentor= RetrofitInteractor(this)


        //Checks if internet is available
        val cm = baseContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = cm.activeNetworkInfo
        if (networkInfo != null && networkInfo.isConnected) {
            if(savedInstanceState==null) {

                //Toast Message to indicate data is loading from the API
                Toast.makeText(baseContext, "Loading....", Toast.LENGTH_SHORT).show()
            }

        }
        else{
            if(savedInstanceState==null) {

                ////Toast Message to prompt user to check his internet connection
                Toast.makeText(baseContext, "Please check your internet connection and try again", Toast.LENGTH_LONG).show()
            }
        }

        //This condition statement handles orientation change and will not call the API again as data is store locally
        if(savedInstanceState!=null ){
            data=savedInstanceState.getParcelable<FlickrDataModel>("sri")
            if(data!=null){
            imageUrlList = data!!.items
            highlight=savedInstanceState.getInt("viewPagerPosition")
            initViewPager()
            viewpager.currentItem=highlight
            initRecyclerView()
            }
        }
         else {
            //Data is fetched from the flickr API
            mainpresentor.getDataFromServer()
        }


        // View pager will hadle page changes below
         viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
             override fun onPageScrollStateChanged(state: Int) {
             }
             override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
             }
             override fun onPageSelected(position: Int) {
                 highlight=position
                 initRecyclerView()
                 linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
                 linearLayoutManager.scrollToPositionWithOffset(position, 0)
                 lastPosition=position
             }
         })
    }


    //Override Method to load the data to the view
    override fun imageList(imagesUrlList: FlickrDataModel) {
        data=imagesUrlList
        initViewPager()
        initRecyclerView()
    }
    //Initialising view pager
     fun initViewPager(){
        mainViewPagerAdapter = MainViewPagerAdapter(applicationContext, data!!.items)
        viewpager.setAdapter(mainViewPagerAdapter)
    }
    //Initialising Recycler view
    fun initRecyclerView(){
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext, LinearLayout.HORIZONTAL, false)
        rvAdapter= ThumbNailAdapter(applicationContext!!, data!!.items, viewpager, highlight)
        recyclerView.adapter = rvAdapter
    }

    //Store data model on orientation change
    override fun onSaveInstanceState(outState: Bundle?) {

        if(data!=null){
            outState!!.putParcelable("sri",data)
            outState!!.putInt("viewPagerPosition",viewpager.currentItem)
            super.onSaveInstanceState(outState)
        }

    }
    //Inflating Menu Options
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.refresh_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    // Data is fetched from the server
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == R.id.menu_add) {
            mainpresentor.getDataFromServer()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}


