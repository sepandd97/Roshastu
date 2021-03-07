package com.roshastudio.roshastu.view

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.roshastudio.roshastu.R
import com.roshastudio.roshastu.adpters.ProductSpecifiactionsAdp
import com.roshastudio.roshastu.databinding.DialogIntroProductFragmentBinding
import com.roshastudio.roshastu.databinding.DialogSpecfictionRvProductFragmentBinding
import com.roshastudio.roshastu.model.ProductSpecification
import kotlinx.android.synthetic.main.dialog_spec_first_rv_items.*
import java.util.ArrayList

class SpecificationsDialogFragment : DialogFragment() {
    val TAG = "example_dialog"
    private var toolbar: Toolbar? = null
    lateinit var binding: DialogSpecfictionRvProductFragmentBinding
    private val adp = ProductSpecifiactionsAdp()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme_FullScreenDialog);
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.dialog_specfiction_rv_product_fragment, container, false
        )
        binding.apply {
            toolbar = toolbarSpecDialog
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState);
        toolbar!!.setNavigationOnClickListener { v: View? -> dismiss() }
        toolbar!!.title = "مشخصات محصول"
        toolbar!!.setOnMenuItemClickListener { item: MenuItem? ->
            dismiss()
            true
        }
        val productSpecification:ArrayList<ProductSpecification>? = arguments?.getParcelableArrayList("list")

        setSpecFirsRv(productSpecification)

    }

    override fun onStart() {
        super.onStart()
        val dialog: Dialog? = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            dialog.window!!.setLayout(width, height)
            dialog.window!!.setWindowAnimations(R.style.AppTheme_Slide);

        }
    }

    private fun setSpecFirsRv(item: ArrayList<ProductSpecification>?) {
        binding.apply {
            if (item != null) {
                adp.submitList(item as List<ProductSpecification>)
            }
            Log.e("00",item!!.size.toString())
            specDialogFirstRv.layoutManager = LinearLayoutManager(requireContext())
            specDialogFirstRv.isNestedScrollingEnabled = false
            specDialogFirstRv.adapter = adp
        }
    }
}