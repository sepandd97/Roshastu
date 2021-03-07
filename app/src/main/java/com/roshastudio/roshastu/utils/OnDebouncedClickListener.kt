package com.roshastudio.roshastu.utils

import android.view.View
import androidx.core.view.postDelayed

class OnDebouncedClickListener(private val delayInMilliSeconds: Long, val action: () -> Unit) : View.OnClickListener {
    var enable = true

    override fun onClick(view: View?) {
        if (enable) {
            enable = false
            view?.postDelayed(delayInMilliSeconds) { enable = true }
            action()
        }
    }
}

fun View.setOnDebouncedClickListener(delayInMilliSeconds: Long , action: () -> Unit) {
    val onDebouncedClickListener = OnDebouncedClickListener(delayInMilliSeconds, action)
    setOnClickListener(onDebouncedClickListener)
}