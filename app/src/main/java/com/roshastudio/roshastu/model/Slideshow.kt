package com.roshastudio.roshastu.model

import com.roshastudio.roshastu.interfaces.DelegateAdapterItem

data class Slideshow( var id: Int, var img: List<SliderImage>) : DelegateAdapterItem {
    override fun id(): Any = id


    override fun content(): Any = SlideShowContent(img)
    override fun payload(other: Any): DelegateAdapterItem.Payloadable {
        if (other is Slideshow) {


            if (img != other.img) {
                return ChangePayload.imgChanged(other.img)
            }

        }
        return DelegateAdapterItem.Payloadable.None
    }

    inner class SlideShowContent( val img: List<SliderImage>) {
        override fun equals(other: Any?): Boolean {
            if (other is SlideShowContent) {
                return img == other.img
            }
            return false
        }
    }

    sealed class ChangePayload : DelegateAdapterItem.Payloadable {

        data class imgChanged(val img: List<SliderImage>) : ChangePayload()

    }

    override fun equals(other: Any?): Boolean {
        if (javaClass != other?.javaClass) {
            return false
        }
        other as Slideshow
        if (id != other.id) {
            return false
        }

        if (img != other.img) {
            return false
        }

        return true
    }

}

