package com.roshastudio.roshastu.adpters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.islamkhsh.CardSliderAdapter
import com.roshastudio.roshastu.R
import com.roshastudio.roshastu.databinding.HomeVerticalRvItemBinding
import com.roshastudio.roshastu.databinding.HomeViewpagerSliderItemBinding
import com.roshastudio.roshastu.model.SliderImage
import com.roshastudio.roshastu.model.Slideshow
import kotlinx.android.synthetic.main.home_viewpager_slider_item.view.*

class HomeViewpagerSliderAdp: CardSliderAdapter<HomeViewpagerSliderAdp.sliderViewHolder>() {
    lateinit var slides: List<SliderImage>
fun getSliders(data:List<SliderImage>){
    slides =data
}

    inner class sliderViewHolder(item:HomeViewpagerSliderItemBinding): RecyclerView.ViewHolder(item.root){}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): sliderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itembinding:HomeViewpagerSliderItemBinding = DataBindingUtil.inflate(inflater,R.layout.home_viewpager_slider_item,parent,false)

        return sliderViewHolder(itembinding)
    }

    @SuppressLint("ResourceAsColor")
    override fun bindVH(holder: sliderViewHolder, position: Int) {
       val img = holder.itemView.home_slider_imageview
         }

    override fun getItemCount(): Int {
        return slides.size
    }

}