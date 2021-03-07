package com.roshastudio.roshastu.view

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.roshastudio.roshastu.R
import com.roshastudio.roshastu.adpters.ProductsAllCommentsAdp
import com.roshastudio.roshastu.databinding.DialogIntroProductFragmentBinding
import kotlinx.android.synthetic.main.fragment_products_detail.*


class IntroDialogFragment:DialogFragment() {
    val TAG = "example_dialog"
    private var toolbar: Toolbar? = null
    lateinit var binding: DialogIntroProductFragmentBinding


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
            R.layout.dialog_intro_product_fragment, container, false
        )
        binding.apply {
            toolbar=toolbarIntroDialog

        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState);
        toolbar!!.setNavigationOnClickListener { v: View? -> dismiss() }
        toolbar!!.title = "توضیحات محصول"
        toolbar!!.setOnMenuItemClickListener { item: MenuItem? ->
            dismiss()
            true
        }
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
}
