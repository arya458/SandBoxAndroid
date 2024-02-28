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
        val date: Int,
)
