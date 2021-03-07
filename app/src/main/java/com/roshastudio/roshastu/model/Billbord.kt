package com.roshastudio.roshastu.model

import com.roshastudio.roshastu.interfaces.DelegateAdapterItem

data class Billbord(val id:Int,val link:String?,val img:String):DelegateAdapterItem {
    override fun id(): Any= id

    override fun content(): Any =Billbordcontent(link!!,img)
    override fun payload(other: Any): DelegateAdapterItem.Payloadable {
        if (other is Billbord) {
            if (link != other.link) {
                return ChangePayload.linkChanged(other.link!!)
            }
            if (img != other.img) {
                return ChangePayload.imgChanged(other.img)
            }

        }
        return DelegateAdapterItem.Payloadable.None
    }
    inner class Billbordcontent(val Link: String,val Img: String){
        override fun equals(other: Any?): Boolean {
            if (other is Billbord) {
                return Link == other.link && Img == other.img
            }
            return false
        }
    }
    sealed class ChangePayload : DelegateAdapterItem.Payloadable {
        data class linkChanged(val link: String) : ChangePayload()
        data class imgChanged(val img: String) : ChangePayload()

    }
    override fun equals(other: Any?): Boolean {
        if (javaClass != other?.javaClass) {
            return false
        }
        other as Billbord
        if (id != other.id) {
            return false
        }
        if (img != other.img) {
            return false
        }
        return true
    }

}