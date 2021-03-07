package com.roshastudio.roshastu.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.roshastudio.roshastu.R
import com.roshastudio.roshastu.databinding.ProductListsFragmentsBinding
import com.roshastudio.roshastu.databinding.ProfileFragmentBinding

class ProfileFragment:Fragment() {
    private lateinit var binding: ProfileFragmentBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.profile_fragment,container,false)



        return binding.root
    }
}