package com.roshastudio.roshastu.model

import com.roshastudio.roshastu.enum.CommentStatus

data class Comments(val id:Int?,val userId:Int?,val productId:Int?, val username:String?, val title:String?, val description:String?, val date:String?,val status: CommentStatus?) {
}