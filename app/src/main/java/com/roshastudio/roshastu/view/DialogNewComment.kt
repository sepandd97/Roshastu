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
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.roshastudio.roshastu.R
import com.roshastudio.roshastu.databinding.DialogProductNewCommentBinding
import com.roshastudio.roshastu.databinding.DialogProductsAllCommentsBinding

class DialogNewComment: BottomSheetDialogFragment() {
    private var toolbar: Toolbar? = null
   var binding: DialogProductNewCommentBinding? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.BottomSheetItem);
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.dialog_product_new_comment, container, false
        )
        binding!!.apply {
            toolbar=toolbarNewcommentDialog
        }
        return binding!!.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState);
        toolbar!!.setNavigationOnClickListener { v: View? -> dismiss() }
        toolbar!!.title = "تمام نظرات"
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
            val height = ViewGroup.LayoutParams.WRAP_CONTENT
            dialog.window!!.setLayout(width, height)
            dialog.window!!.setWindowAnimations(R.style.AppTheme_Slide);
        }

    }
    // preventing bottom sheet dialog close on outside view touch
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return (super.onCreateDialog(savedInstanceState) as BottomSheetDialog).apply {
            setCanceledOnTouchOutside(false)
        }
    }
}