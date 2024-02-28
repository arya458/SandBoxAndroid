package com.arya.danesh.myresume.data.response




data class PostResponse(
        val blog_image:String,
        val blog_tittle:String,
        val blog_date:String,
        val id: Int,
        val posts: List<PostText>)
data class PostText(
        val id: Int,
        val tittle: String,
        val data: String,
        val type: String,
)