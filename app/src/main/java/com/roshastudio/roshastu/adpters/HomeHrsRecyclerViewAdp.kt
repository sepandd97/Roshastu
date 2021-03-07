package com.roshastudio.roshastu.adpters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import com.roshastudio.roshastu.R
import com.roshastudio.roshastu.databinding.HomeHorizentalRvInvisibleItemBinding
import com.roshastudio.roshastu.databinding.HomeHorizontalRvItemBinding
import com.roshastudio.roshastu.interfaces.DelegateAdapterItem
import com.roshastudio.roshastu.model.Product
import com.roshastudio.roshastu.viewModels.FragmentHomeVm
import kotlinx.android.synthetic.main.home_horizontal_rv_item.view.*

private lateinit var vmFragHome: FragmentHomeVm

class HomeHrsRecyclerViewAdp(private var mContext: Context,val catId:String):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var product:List<Product> ?=null
    private var isInvisible = true
    private val INVISIBLE_VIEW_TYPE = 100
    private var Click:ClickHrsAdapter? = null
    fun getHrData(Data: List<Product>){
        product=Data

    }
fun getClick( click:ClickHrsAdapter? = null){
    Click =click
}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        if (viewType == INVISIBLE_VIEW_TYPE){
            val bindingInvisible:HomeHorizentalRvInvisibleItemBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.home_horizental_rv_invisible_item,
                parent,
                false
            )
            return  CarouselInvisbileItemViewHolder(bindingInvisible)
        }else{
            val binding:HomeHorizontalRvItemBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.home_horizontal_rv_item,
                parent,
                false
            )
            return CarouselItemviewHolder(binding)
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CarouselItemviewHolder){
            if (isInvisible){
                position - 1
                val product= product!![position - 1]
                val vh: CarouselItemviewHolder = holder
                vh.bindProductItem(product)
                vmFragHome =  ViewModelProvider(mContext as ViewModelStoreOwner).get(FragmentHomeVm::class.java)
            }

        }


    }

    override fun getItemCount(): Int {
        if (product == null)
            return 0;
        if (isInvisible)
            return product!!.size+1;
        return product!!.size
    }
    override fun getItemViewType(position: Int): Int {
        return if (isInvisible && position == 0) INVISIBLE_VIEW_TYPE else super.getItemViewType(position)
    }
    inner class CarouselItemviewHolder(item: HomeHorizontalRvItemBinding):RecyclerView.ViewHolder(item.root){
        var bind=item
        fun bindProductItem(prItem:Product){
            itemView.rv_item_horizental_layout.setOnClickListener {
                Click!!.onItemClick(prItem)

            }
          bind.productitem = prItem
        }

    }
    class CarouselInvisbileItemViewHolder(invisibleItem: HomeHorizentalRvInvisibleItemBinding) : RecyclerView.ViewHolder(
        invisibleItem.root
    )
    interface ClickHrsAdapter {
        fun onItemClick(item: Product)
    }
}