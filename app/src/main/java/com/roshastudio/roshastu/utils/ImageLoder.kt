package com.roshastudio.roshastu.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

object ImageLoder {
    @BindingAdapter("app:imgLoderDrwable")
    @JvmStatic
    fun imgLoderDrwable(imgeView: ImageView, url: Int) {
        Glide.with(imgeView).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).dontAnimate()
            .fitCenter().into(imgeView)
    }


}