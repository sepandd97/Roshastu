package com.roshastudio.roshastu.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.roshastudio.roshastu.R
import com.roshastudio.roshastu.adpters.ProductDetailImageSliderAdapter
import com.roshastudio.roshastu.databinding.ProductListsFragmentsBinding
import kotlinx.android.synthetic.main.app_bar_main.*

class ProductListFragment:Fragment() {
    private lateinit var binding:ProductListsFragmentsBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       binding = DataBindingUtil.inflate(inflater, R.layout.product_lists_fragments,container,false)



        return binding.root
    }

    override fun onActivityCreated(@Nullable savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity as MainActivity?)!!.setToolbar(toolbarMain)

    }
    override fun onDestroyView() {
        (activity as MainActivity?)!!.setToolbar(null)
        super.onDestroyView()
    }
}