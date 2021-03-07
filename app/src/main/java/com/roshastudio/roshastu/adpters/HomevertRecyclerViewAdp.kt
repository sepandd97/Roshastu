package com.roshastudio.roshastu.adpters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper
import com.roshastudio.roshastu.R
import com.roshastudio.roshastu.baseClass.BaseViewHolder
import com.roshastudio.roshastu.databinding.HomeBillbordsItemBinding
import com.roshastudio.roshastu.databinding.HomeVerticalRvItemBinding
import com.roshastudio.roshastu.databinding.SlideshowHomeFragmentItemBinding
import com.roshastudio.roshastu.interfaces.DelegateAdapterItem
import com.roshastudio.roshastu.model.*
import com.roshastudio.roshastu.utils.EqualSpacingItemDecoration
import kotlinx.android.synthetic.main.home_vertical_rv_item.view.*
import kotlinx.android.synthetic.main.slideshow_home_fragment_item.view.*
import kotlin.math.abs


class HomevertRecyclerViewAdp(
    private val interaction: Interaction? = null,
    val context: Context,
    private val subItemclick: HomeHrsRecyclerViewAdp.ClickHrsAdapter? = null

) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {
    private lateinit var data: List<Product>
    private val CAROUSEL_PARALLAX_BG_VIEW_TYPE = 100

    companion object {
        private const val PRODUCT_LIST = 0
        private const val SLID_SHOW = 1
        private const val BILBORD = 2
    }

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<HomeView>() {

        override fun areItemsTheSame(
            oldItem: HomeView,
            newItem: HomeView
        ): Boolean {
            return oldItem::class == newItem::class && oldItem.type == newItem.type
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: HomeView,
            newItem: HomeView
        ): Boolean {
            return oldItem == newItem && oldItem.data.content() == newItem.data.content() && oldItem.data.id() == newItem.data.id()
        }

        override fun getChangePayload(oldItem: HomeView, newItem: HomeView): Any? {
            return oldItem.data.payload(newItem.data)
        }

    }

    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val inflater = LayoutInflater.from(parent.context)

         return when (viewType) {
             PRODUCT_LIST -> {
                val holder=  ItemViewHolder(DataBindingUtil.inflate(inflater, R.layout.home_vertical_rv_item, parent, false), interaction)

                 holder
             }
             SLID_SHOW ->
                   SlideShowViewHolder(
                     DataBindingUtil.inflate(
                         inflater,
                         R.layout.slideshow_home_fragment_item,
                         parent,
                         false
                     ), interaction
                 )


             BILBORD -> BillbordViewholder(
                 DataBindingUtil.inflate(
                     inflater,
                     R.layout.home_billbords_item,
                     parent,
                     false
                 ), interaction
             )
            else -> throw IllegalArgumentException("Invalid view type")
        }

    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is ItemViewHolder -> {
                val data = differ.currentList[position].data as Products_list

                holder.alphaView.setBackgroundColor(Color.parseColor(data.itemFgColor))
                holder.rvHorizental.addOnScrollListener(object :
                    RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        val v = holder.linearLayoutManager.getChildAt(0)
                        // Check if the the visible item is the first item
                        if (holder.linearLayoutManager.findFirstVisibleItemPosition() == 0 && v != null) {

                            // Get view of the first item
                            val firstVisibleItem: View? =
                                holder.linearLayoutManager.findViewByPosition(holder.linearLayoutManager.findFirstVisibleItemPosition())
                            val distanceFromLeft: Float =
                                firstVisibleItem!!.left.toFloat() // distance from the left
                            val translateX = distanceFromLeft * 0.10f // move x distance
                            holder.bgImage.translationX = translateX
                            // If the view scroll pass the starting position, change color view alpha
                            val itemSize: Float =
                                firstVisibleItem.width.toFloat() // view size
                            val scrollAmount = distanceFromLeft - (2 * itemSize)
                            if (dx < 0) {
                                // view transparency
                                val alpha = (abs(scrollAmount)) / itemSize * 0.20f
                                //Set alpha to image to bring 'fade out' and 'fade in' effect
                                holder.bgImage.alpha = 1 - alpha
                                //Set alpha to color view to bring 'darker' and 'clearer' effect
                                holder.alphaView.alpha = alpha
                            } else if (dx > 0) {
                                val alpha = ((abs(scrollAmount)) / itemSize) * 0.06f
                                //Set alpha to image to bring 'fade out' and 'fade in' effect
                                holder.bgImage.alpha = 1 - alpha
                                //Set alpha to color view to bring 'darker' and 'clearer' effect
                                holder.alphaView.alpha = alpha

                            } else {
                                //Set alpha to image to bring 'fade out' and 'fade in' effect
                                holder.bgImage.alpha = 1f
                                //Set alpha to color view to bring 'darker' and 'clearer' effect
                                holder.alphaView.alpha = 0f
                            }
                        }
                        super.onScrolled(recyclerView, dx, dy)
                    }
                })

                if (holder.horizontalAdapter == null) {

                    holder.setCarouselAdp(context, data)
                } else {

                    holder.updateCarouselAdp(context, data)
                }

                holder.bind(data)
            }
            is SlideShowViewHolder -> {
                val data = differ.currentList[position].data
                        as Slideshow
                holder.bind(data)
            }
            is BillbordViewholder -> {
                val data = differ.currentList[position].data
                        as Billbord
                holder.bind(data)
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun getItemViewType(position: Int): Int {
        val comparable = differ.currentList[position].type
        return when (comparable) {
            0 -> PRODUCT_LIST
            1 -> SLID_SHOW
            2 -> BILBORD
            else -> throw IllegalArgumentException("Invalid type of data " + position)
        }
    }

    fun submitList(list: List<HomeView>) {
        differ.submitList(list)
    }

    fun list(list: List<HomeView>) {
        submitList(list)

    }

    inner class ItemViewHolder
    constructor(itemView: HomeVerticalRvItemBinding, private val interaction: Interaction?) :
        BaseViewHolder<Products_list>(itemView.root) {
        val bindingitem = itemView
        var context = itemView.root.context
        var horizontalAdapter: HomeHrsRecyclerViewAdp? =  null
        val alphaView = itemView.viewholderCarouselBgParallaxAlphaView
        val rvHorizental = itemView.horizontalRv
        var bgImage = itemView.viewholderCarouselBgParallaxBgIv
        val linearLayoutManager = LinearLayoutManager(
            itemView.root.context,
            LinearLayoutManager.HORIZONTAL,
            false
        )

        //initializing second recycler view And adpter
        fun setCarouselAdp(context: Context, item: Products_list) {
            rvHorizental.layoutManager = linearLayoutManager
            rvHorizental.addItemDecoration(
                EqualSpacingItemDecoration(
                    16,
                    EqualSpacingItemDecoration.HORIZONTAL
                )
            )
            horizontalAdapter=HomeHrsRecyclerViewAdp(context, "ss")
            //Adding snapping effect onto recycler view
            val snapHelperStart: SnapHelper = GravitySnapHelper(Gravity.START)
            snapHelperStart.attachToRecyclerView(rvHorizental)
            rvHorizental.setHasFixedSize(true);
            rvHorizental.setNestedScrollingEnabled(false);
            rvHorizental.adapter = horizontalAdapter
            data = item.pItems
            horizontalAdapter!!.getClick(subItemclick)
            horizontalAdapter!!.getHrData(data)
                //Adding snapping effect onto recycler view

        }
        fun updateCarouselAdp(context: Context, item: Products_list) {
                data = item.pItems
                horizontalAdapter!!.getHrData(data)
                horizontalAdapter!!.notifyDataSetChanged()

        }
        override fun bind(item: Products_list) {
            bindingitem.products = item

            bindingitem.categoryMore.setOnClickListener {
                interaction?.onItemSelected(bindingAdapterPosition, item)
            }
            itemView.appCompatTextView.text = item.titel
            //send product data to second recycler view adapter
        }

    }

    inner class SlideShowViewHolder
    constructor(itemView: SlideshowHomeFragmentItemBinding, private val interaction: Interaction?) :
        BaseViewHolder<Slideshow>(itemView.root) {
        var context = itemView.root.context
        val bindingSlide = itemView
        val slideradp = HomeViewpagerSliderAdp()
        fun setSliderAdapter(context: Context, img: List<SliderImage>) {
            bindingSlide.apply {
                slideradp.getSliders(img)
                homeSliderViewpager.isNestedScrollingEnabled = false
                homeSliderViewpager.adapter = slideradp
            }
        }


        override fun bind(item: Slideshow) {
            itemView.setOnClickListener {
                interaction?.onItemSelected(bindingAdapterPosition, item)
            }
            setSliderAdapter(context, item.img)

        }
    }

    inner class BillbordViewholder
    constructor(itemView: HomeBillbordsItemBinding, private val interaction: Interaction?) :
        BaseViewHolder<Billbord>(itemView.root) {
        override fun bind(item: Billbord) {
            itemView.setOnClickListener {
                interaction?.onItemSelected(bindingAdapterPosition, item)
            }
        }

    }

       interface Interaction {
        fun onItemSelected(position: Int, item: DelegateAdapterItem)
    }

}