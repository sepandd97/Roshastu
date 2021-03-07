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
import com.roshastudio.roshastu.adpters.ProductsAllCommentsAdp
import com.roshastudio.roshastu.databinding.DialogIntroProductFragmentBinding
import com.roshastudio.roshastu.databinding.DialogProductsAllCommentsBinding
import com.roshastudio.roshastu.enum.CommentStatus
import com.roshastudio.roshastu.model.Comments
import com.roshastudio.roshastu.model.ProductSpecification
import kotlinx.android.synthetic.main.dialog_products_all_comments.*
import java.util.ArrayList

class DialogAllComments: DialogFragment() {
    private var toolbar: Toolbar? = null
    lateinit var binding: DialogProductsAllCommentsBinding
    private var Adp= ProductsAllCommentsAdp()

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
            R.layout.dialog_products_all_comments, container, false
        )
        binding.apply {
            toolbar=toolbarAllCommentsDialog
        }
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState);
        toolbar!!.setNavigationOnClickListener { v: View? -> dismiss() }
        toolbar!!.title = "تمام نظرات"
        toolbar!!.setOnMenuItemClickListener { item: MenuItem? ->
            dismiss()
            true
        }
        val cm = arrayListOf<Comments>(
            Comments(
                1,
                1,
                1,
                "محمد",
                "معرکست",
                getString(R.string.loremipsum_long),
                "1/1/1"
                ,CommentStatus.APPROVE),
            Comments(1, 1, 1, "محمد", "معرکست", getString(R.string.loremipsum_long), "1/1/1" ,CommentStatus.APPROVE),
            Comments(1, 1, 1, "پویا", "عالی", getString(R.string.loremipsum_long), "1/1/1" ,CommentStatus.APPROVE),
            Comments(1, 1, 1, "احمد", "بهترین", getString(R.string.loremipsum_long), "1/1/1" ,CommentStatus.APPROVE)
        )
        setCommentsRv(cm)
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
    private fun setCommentsRv(item: ArrayList<Comments>?) {
        binding.apply {
            if (item != null) {
                Adp.getComments(item as List<Comments>)
            }
            rvAllComments.layoutManager = LinearLayoutManager(requireContext())
            rvAllComments.isNestedScrollingEnabled = false
            rvAllComments.adapter = Adp
        }
    }
}