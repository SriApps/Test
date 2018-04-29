package com.test.sri.test.Adapter

import android.content.Context
import android.support.v4.view.ViewPager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.test.sri.test.Model.Item
import com.test.sri.test.R

/**
 * Created by Myworld on 21/04/2018.
 */
 class ThumbNailAdapter(private val context: Context, private val image_ArrayList: List<Item>, private val viewPager:ViewPager, private val highlight:Int) : RecyclerView.Adapter<ThumbNailAdapter.ViewHolder>() {

    //Initialising variables
    private var itemHighlighted =true
    private val thumbNailHeight = 75
    private val thumbNailWidth = 75

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val v= LayoutInflater.from(parent?.context).inflate(R.layout.thunbnail_layout,parent,false)
        return ViewHolder(v)
    }
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {

        //Picasso library is used to display data into image View
        Picasso.with(context)
                .load(image_ArrayList.get(position).media.m)
                .resize(thumbNailWidth,thumbNailHeight)
                .error(R.drawable.ic_launcher_background)
                .into(holder!!.thumbNail_IV)

        //This conditiong is used to set highlighted border to relevant cardview
        if(highlight==position){
            itemHighlighted=false
            holder.thumbNail_IV.setBackgroundResource(R.drawable.drawable_boder)
        }else if(highlight!=position&&itemHighlighted==false){
            holder.thumbNail_IV.setBackgroundResource(R.drawable.drawable_boder_default)
        }

        //This will display the clicked thumbnail image to ViewPager
        holder.thumbNail_IV.setOnClickListener({
             viewPager.currentItem = position
        })
    }
    override fun getItemCount(): Int {
       return image_ArrayList.size
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var thumbNail_IV=itemView.findViewById<ImageView>(R.id.thumbNail_IV)
    }
}