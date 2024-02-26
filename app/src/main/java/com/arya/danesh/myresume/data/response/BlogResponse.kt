package com.arya.danesh.myresume.data.response

data class BlogResponse(

        val key: String,
        val blog: List<BlogPost>

)

data class BlogPost(

        val id: Int,
        val tittle: String,
        val image: String,
        val detail: String,
        val post: List<PostText>,
        val date: Int,
        val src: List<PostSrc>,
)

data class PostText(
        val id: Int,
        val text: String,
        val src: String,
        val type: String,

        )
data class PostSrc(

        val link : String

)