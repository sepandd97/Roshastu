package com.roshastudio.roshastu.adpters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.roshastudio.roshastu.R
import com.roshastudio.roshastu.databinding.ProductsAttributesItemsBinding
import com.roshastudio.roshastu.model.ProductAtributes

class ProductDetailAtributeAdp(private val interaction: Interaction? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ProductAtributes>() {

        override fun areItemsTheSame(oldItem: ProductAtributes, newItem: ProductAtributes): Boolean {
            return oldItem::class == newItem::class
        }

        override fun areContentsTheSame(
            oldItem: ProductAtributes,
            newItem: ProductAtributes
        ): Boolean {
            return oldItem == newItem && oldItem.titelDesc == newItem.titelDesc && oldItem.titel == newItem.titel && oldItem.id == newItem.id
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding:ProductsAttributesItemsBinding = DataBindingUtil.inflate(inflater, R.layout.products_attributes_items, parent, false)
        return Atributsviewholder(binding,interaction)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is Atributsviewholder -> {
                holder.bind(differ.currentList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<ProductAtributes>) {
        differ.submitList(list)
    }

    inner class Atributsviewholder
    constructor(
        itemView: ProductsAttributesItemsBinding,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView.root) {
        private val bindingitem =itemView
        fun bind(item: ProductAtributes) = with(itemView) {
            bindingitem.attributs = item
            itemView.setOnClickListener {
                interaction?.onItemSelected(bindingAdapterPosition, item)
            }


        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: ProductAtributes)
    }
}