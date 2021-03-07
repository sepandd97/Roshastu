package com.roshastudio.roshastu.adpters

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.roshastudio.roshastu.R
import com.roshastudio.roshastu.databinding.DialogSpecFirstRvItemsBinding
import com.roshastudio.roshastu.databinding.ProductsAttributesItemsBinding
import com.roshastudio.roshastu.model.ProductSpecification
import com.roshastudio.roshastu.model.Specification
import java.util.ArrayList

class ProductSpecifiactionsAdp(private val interaction: Interaction? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ProductSpecification>() {

        override fun areItemsTheSame(
            oldItem: ProductSpecification,
            newItem: ProductSpecification
        ): Boolean {
            return oldItem::class == newItem::class
        }

        override fun areContentsTheSame(
            oldItem: ProductSpecification,
            newItem: ProductSpecification
        ): Boolean {
            return oldItem == newItem && oldItem.title == newItem.title && oldItem.specification == newItem.specification && oldItem.id == newItem.id
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: DialogSpecFirstRvItemsBinding =
            DataBindingUtil.inflate(inflater, R.layout.dialog_spec_first_rv_items, parent, false)
        return SpecViewholder(binding, interaction)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SpecViewholder -> {
                holder.bind(differ.currentList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<ProductSpecification>) {
        differ.submitList(list)
    }

    class SpecViewholder
    constructor(
        itemView: DialogSpecFirstRvItemsBinding,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView.root) {
        val adp = ProductSpecifictionSecondApd()
        val binding = itemView

        fun bind(item: ProductSpecification) = with(itemView) {
            setSecondRv(item.specification)
            itemView.setOnClickListener {
                interaction?.onItemSelected(bindingAdapterPosition, item)
            }
            Log.e("first",item.title.toString())
            binding.productSpec = item
        }

        private fun setSecondRv(item: ArrayList<Specification>?) {
            binding.apply {
                adp.getSpecification(item as List<Specification>)
                specDialogSecondRv.layoutManager = LinearLayoutManager(itemView.context)
                specDialogSecondRv.isNestedScrollingEnabled = false
                specDialogSecondRv.adapter = adp
            }
        }

    }

    interface Interaction {
        fun onItemSelected(position: Int, item: ProductSpecification)
    }
}