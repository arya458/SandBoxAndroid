package com.arya.danesh.myresume.data.response




data class PostResponse(
        val blogkey:String,
        val id: Int,
        val posts: List<PostText>)
data class PostText(
        val id: Int,
        val tittle: String,
        val data: String,
        val type: String,
)