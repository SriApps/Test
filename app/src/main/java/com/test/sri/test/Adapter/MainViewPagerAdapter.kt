package com.test.sri.test.Adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.squareup.picasso.Picasso
import com.test.sri.test.Model.Item
import com.test.sri.test.R

/**
 * Created by Myworld on 21/04/2018.
 */
class MainViewPagerAdapter(private val context:Context,private val image_ArrayList: List<Item>) : PagerAdapter() {
    //Initialising variables
private var layoutInflator: LayoutInflater? = null

    override fun isViewFromObject(view: View?, `object`: Any?): Boolean {
        return view==`object`as LinearLayout
    }

    override fun getCount(): Int {
        return image_ArrayList.size

    }
    override fun getItemPosition(`object`: Any?): Int {
        return super.getItemPosition(`object`)
    }
    override fun instantiateItem(container: ViewGroup?, position: Int): Any {

            layoutInflator= context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var itemView:View= layoutInflator!!.inflate(R.layout.image_layout,container,false)
            var imageView=itemView.findViewById<ImageView>(R.id.test_IV)
            Picasso.with(context)
                    .load(image_ArrayList.get(position).media.m)
                    .fit()
                    .error(R.drawable.ic_launcher_background)
                    .into(imageView)
            container?.addView(itemView)
            return itemView
    }
     override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
        container!!.removeView(`object`as LinearLayout)
    }
}