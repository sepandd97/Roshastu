package com.roshastudio.roshastu.adpters

import android.content.res.Resources
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.graphics.drawable.DrawableWrapper
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.engine.Resource
import com.roshastudio.roshastu.R
import com.roshastudio.roshastu.databinding.DialogSpecFirstRvItemsBinding
import com.roshastudio.roshastu.databinding.DialogSpecSecondRvItemBinding
import com.roshastudio.roshastu.model.ProductSpecification
import com.roshastudio.roshastu.model.Specification

class ProductSpecifictionSecondApd(): RecyclerView.Adapter<ProductSpecifictionSecondApd.specAttrViewholder>() {
    private var specifiction:List<Specification> ?=null

    fun getSpecification(list: List<Specification>) {
        specifiction=list
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): specAttrViewholder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: DialogSpecSecondRvItemBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_spec_second_rv_item, parent, false)

        return specAttrViewholder(binding)
    }

    override fun onBindViewHolder(holder: specAttrViewholder, position: Int) {
        holder.apply {
            binding.apply {
                var num:Int? =null
                var colordark = ContextCompat.getColor(itemView.context, R.color.darkerwhite)
                var colorwhite = ContextCompat.getColor(itemView.context, R.color.white)
                    num= position % 2
                    Log.e("TAG", "$position:$num ", )
                    if (num == 0 ){
                        cardSpecDialog.setCardBackgroundColor(colorwhite)
                    }else{
                        cardSpecDialog.setCardBackgroundColor(colordark)

                    }


            }
            bind(specifiction!![position])
        }

    }

    override fun getItemCount(): Int {
    return specifiction!!.size
    }
    inner class specAttrViewholder(itemView:DialogSpecSecondRvItemBinding): RecyclerView.ViewHolder(itemView.root){
        var binding=itemView

        fun bind(spec:Specification){
            binding.specData=spec

        }
    }

}