package com.roshastudio.roshastu.adpters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.roshastudio.roshastu.R
import com.roshastudio.roshastu.databinding.ProductsCatagoryRvItemBinding
import com.roshastudio.roshastu.model.Category
import com.roshastudio.roshastu.utils.OnDebouncedClickListener
import com.roshastudio.roshastu.utils.setOnDebouncedClickListener
import kotlinx.android.synthetic.main.products_catagory_rv_item.view.*

class ProductDetailCategoryAdp(private val interaction: CategoryInteraction? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Category>() {

        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem::class == newItem::class        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem &&  oldItem.name == newItem.name && oldItem.id == newItem.id
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding:ProductsCatagoryRvItemBinding = DataBindingUtil.inflate(inflater, R.layout.products_catagory_rv_item,parent,false)
        return CatViewholder(binding,interaction)


    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CatViewholder -> {
                holder.bind(differ.currentList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<Category>) {
        differ.submitList(list)
    }

    class CatViewholder
    constructor(
        itemView: ProductsCatagoryRvItemBinding,
        private val interaction: CategoryInteraction?
    ) : RecyclerView.ViewHolder(itemView.root) {
val bind = itemView
        fun bind(item: Category) = with(itemView) {
            product_detail_cat_cardview.apply {
                setOnDebouncedClickListener(1000){
                    Toast.makeText(context, item.name.toString(), Toast.LENGTH_SHORT).show()
                    interaction?.onCategorySelected(bindingAdapterPosition, item)
                }
            }
            bind.category=item
        }
    }

    interface CategoryInteraction {
        fun onCategorySelected(position: Int, item: Category)
    }
}