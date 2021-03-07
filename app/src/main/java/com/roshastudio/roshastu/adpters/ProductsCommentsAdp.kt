package com.roshastudio.roshastu.adpters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.roshastudio.roshastu.R
import com.roshastudio.roshastu.databinding.ProductCommentsRvItemBinding
import com.roshastudio.roshastu.databinding.ProductsCommentsRvItemShowMoreBinding
import com.roshastudio.roshastu.model.Comments
import kotlinx.android.synthetic.main.products_comments_rv_item_show_more.view.*

class ProductsCommentsAdp(private val interaction: CommentsInteraction? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val ENDITEM_VIEW_TYPE = 1
    private val NORMAL_VIEW_TYPE = 100
    private val itemLimit = 2
    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Comments>() {

        override fun areItemsTheSame(oldItem: Comments, newItem: Comments): Boolean {
            return oldItem::class == newItem::class
        }

        override fun areContentsTheSame(oldItem: Comments, newItem: Comments): Boolean {
            return oldItem == newItem && oldItem.productId == newItem.productId && oldItem.userId == newItem.userId && oldItem.title == newItem.title && oldItem.description == newItem.description && oldItem.id == newItem.id
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)
    override fun getItemViewType(position: Int): Int {
        return if (position == differ.currentList.size||position==itemLimit ) ENDITEM_VIEW_TYPE else NORMAL_VIEW_TYPE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)


        if (viewType == NORMAL_VIEW_TYPE) {
            val binding: ProductCommentsRvItemBinding =
                DataBindingUtil.inflate(
                    inflater,
                    R.layout.product_comments_rv_item,
                    parent,
                    false
                )
            return CommentsViewHolder(binding, interaction)
        } else {
            val endItemBinding: ProductsCommentsRvItemShowMoreBinding =
                DataBindingUtil.inflate(
                    inflater,
                    R.layout.products_comments_rv_item_show_more,
                    parent,
                    false
                )
            return ShowmorItemViewHolder(endItemBinding, interaction)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {


        when (holder) {
            is CommentsViewHolder -> {
                val comments = differ.currentList[position]
                Log.e("pTAG", "onBindViewHolder: ${differ.currentList.size}")
                holder.bind(comments)
            }
            is ShowmorItemViewHolder -> {
                if (differ.currentList.size>0){
                    val prid = differ.currentList[0].productId
                    holder.bind(prid!!)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        if (differ.currentList.size in 1..itemLimit&& differ.currentList.size> 0) {
        return differ.currentList.size + 1
        }else if (differ.currentList.size>itemLimit&& itemLimit > 0){
            return itemLimit +1
        }
        else if (differ.currentList.size<= 0 || itemLimit <= 0){
            return 0
        }else{
            return differ.currentList.size
        }
   }

    fun submitList(list: List<Comments>) {
        differ.submitList(list)
    }

    class CommentsViewHolder
    constructor(
        itemView: ProductCommentsRvItemBinding,
        private val interaction: CommentsInteraction?
    ) : RecyclerView.ViewHolder(itemView.root) {
        val binding = itemView
        fun bind(item: Comments) = with(itemView) {
            itemView.setOnClickListener {
            }
            binding.comment = item
        }
    }

    class ShowmorItemViewHolder
    constructor(
        itemView: ProductsCommentsRvItemShowMoreBinding,
        private val interaction: CommentsInteraction?
    ) : RecyclerView.ViewHolder(itemView.root) {
        val binding = itemView
        fun bind(item: Int) = with(itemView) {
            show_more_card.setOnClickListener {
                interaction!!.showMore(bindingAdapterPosition, item)
            }
        }
    }

    interface CommentsInteraction {
        fun onItemSelected(position: Int, item: Comments){}
        fun showMore(position: Int, item: Int)
    }

}