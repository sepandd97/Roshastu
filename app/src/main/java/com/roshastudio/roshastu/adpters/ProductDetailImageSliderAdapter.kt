package com.roshastudio.roshastu.adpters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.islamkhsh.CardSliderAdapter
import com.roshastudio.roshastu.R
import com.roshastudio.roshastu.databinding.ProductImgsliderItemsBinding
import com.roshastudio.roshastu.model.ProductSlideImages
import com.roshastudio.roshastu.model.SliderImage
import kotlinx.android.synthetic.main.product_imgslider_items.view.*

class ProductDetailImageSliderAdapter:
    CardSliderAdapter<ProductDetailImageSliderAdapter.PrDetailImageSliderViewHolder>() {
    inner class PrDetailImageSliderViewHolder(item:ProductImgsliderItemsBinding): RecyclerView.ViewHolder(item.root){}
    lateinit var slides: List<ProductSlideImages>
    fun getImgSliders(data:List<ProductSlideImages>){
        slides =data
    }

    override fun bindVH(holder: PrDetailImageSliderViewHolder, position: Int) {
val imgView = holder.itemView.product_detail_imgslider_imageview
    }

    override fun getItemCount(): Int {
       return slides.size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PrDetailImageSliderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding:ProductImgsliderItemsBinding = DataBindingUtil.inflate(inflater,R.layout.product_imgslider_items,parent,false)

  return PrDetailImageSliderViewHolder(binding)
    }

}