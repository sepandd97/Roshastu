package com.roshastudio.roshastu.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.roshastudio.roshastu.R
import com.roshastudio.roshastu.databinding.OrderFragmentBinding

class OrderFragment():Fragment() {
    private lateinit var Binding:OrderFragmentBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Binding = DataBindingUtil.inflate(inflater, R.layout.order_fragment,container,false)



        return Binding.root
    }
}