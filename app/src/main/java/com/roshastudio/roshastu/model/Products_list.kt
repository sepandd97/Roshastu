package com.roshastudio.roshastu.model

import com.roshastudio.roshastu.interfaces.DelegateAdapterItem

data class Products_list(
    var id: Int,
    var titel: String,
    var img: Int,
    var isInvisible:Boolean,
    var itemFgColor:String,
    var pItems: List<Product>
) : DelegateAdapterItem {
    override fun id(): Any = id


    override fun content(): Any = Product_listContent(titel, img, pItems,isInvisible,itemFgColor)
    override fun payload(other: Any): DelegateAdapterItem.Payloadable {
        if (other is Products_list) {
            if (titel != other.titel) {
                return ChangePayload.titelChanged(other.titel)
            }

            if (img != other.img) {
                return ChangePayload.imgChanged(other.img)
            }
            if (isInvisible != other.isInvisible) {
                return ChangePayload.isInvisibleChange(other.isInvisible)
            }
            if (itemFgColor != other.itemFgColor) {
                return ChangePayload.itemFgColorChange(other.itemFgColor)
            }
            if (pItems != other.pItems) {
                return ChangePayload.pItemsChanged(other.pItems)
            }
        }
        return DelegateAdapterItem.Payloadable.None
    }

    inner class Product_listContent(val titel: String, val img: Int, val pItems: List<Product>,val isInvisible:Boolean,val itemFgColor:String) {
        override fun equals(other: Any?): Boolean {
            if (other is Product_listContent) {
                return titel == other.titel && img == other.img && pItems == other.pItems &&  isInvisible == other.isInvisible &&  itemFgColor == other.itemFgColor
            }
            return false
        }
    }

    sealed class ChangePayload : DelegateAdapterItem.Payloadable {
        data class titelChanged(val titel: String) : ChangePayload()
        data class imgChanged(val img: Int) : ChangePayload()
        data class isInvisibleChange(val isInvisible: Boolean) : ChangePayload()
        data class itemFgColorChange(val itemFgColor: String) : ChangePayload()
        data class pItemsChanged(val pItems: List<Product>) : ChangePayload()
    }

    override fun equals(other: Any?): Boolean {
        if (javaClass != other?.javaClass) {
            return false
        }
        other as Products_list
        if (id != other.id) {
            return false
        }
        if (titel != other.titel) {
            return false
        }
        if (img != other.img) {
            return false
        }
        if (isInvisible != other.isInvisible) {
            return false
        }
        if (pItems != other.pItems) {
            return false
        }
        return true
    }

}