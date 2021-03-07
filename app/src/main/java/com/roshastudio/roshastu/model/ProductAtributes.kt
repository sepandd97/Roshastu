package com.roshastudio.roshastu.model

data class ProductAtributes(val id:Int,val titel:String,val titelDesc:String) {
    override fun equals(other: Any?): Boolean {
        if (javaClass != other?.javaClass) {
            return false
        }
        other as ProductAtributes
        if (id != other.id) {
            return false
        }
        if (titel != other.titel) {
            return false
        }
        if (titel != other.titel) {
            return false
        }
        if (titelDesc != other.titelDesc) {
            return false
        }
        return true
    }

}