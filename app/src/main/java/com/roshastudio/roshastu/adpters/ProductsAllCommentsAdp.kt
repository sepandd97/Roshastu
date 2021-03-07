package com.roshastudio.roshastu.adpters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.roshastudio.roshastu.R
import com.roshastudio.roshastu.baseClass.BaseViewHolder
import com.roshastudio.roshastu.databinding.DialogProductsAllCommentsItemBinding
import com.roshastudio.roshastu.model.Comments
import java.util.zip.Inflater

class ProductsAllCommentsAdp(): RecyclerView.Adapter<ProductsAllCommentsAdp.ViewHolderComments>() {
    private var comments:List<Comments>? = null
    private lateinit var binding:DialogProductsAllCommentsItemBinding
   fun getComments(data:List<Comments>?){
       comments=data
       notifyDataSetChanged()
   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderComments {
        val inflater = LayoutInflater.from(parent.context)
        binding=DataBindingUtil.inflate(inflater, R.layout.dialog_products_all_comments_item,parent,false)
        binding.apply {

        }
        return ViewHolderComments(binding)
    }

    override fun onBindViewHolder(holder: ViewHolderComments, position: Int) {
        holder.bind(comments!![position])
    }

    override fun getItemCount(): Int {
return comments!!.size
    }
    inner class ViewHolderComments(itemView:DialogProductsAllCommentsItemBinding): RecyclerView.ViewHolder(itemView.root){
        val binding = itemView

        fun bind(item:Comments){
            binding.comment=item
        }
    }
}