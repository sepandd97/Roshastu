package com.roshastudio.roshastu.model

import java.util.ArrayList

data class Product(
        var id:String,
        var itemTitel:String,
        var itemImage:Int,
        var itemPrice:String,
        var slideImages:List<ProductSlideImages>?,
        var atributes:List<ProductAtributes>?,
        var beforeDisCount:String?,
        var productIntroducingDesc:String?,
        var productSpecifications:ArrayList<ProductSpecification>?,
        var productCategory:List<Category>?,
        var productComments:List<Comments>?


) {
}