package com.arya.danesh.myresume.data.response

import com.google.gson.annotations.SerializedName


data class PostResponse (

        @SerializedName("blog_image"  ) var blogImage  : String          = "null",
        @SerializedName("blog_tittle" ) var blogTittle : String          = "null",
        @SerializedName("blog_date"   ) var blogDate   : String          = "null",
        @SerializedName("id"          ) var id         : Int             = 0,
        @SerializedName("posts"       ) var posts      : ArrayList<PostText> = arrayListOf()

)
data class PostText (

        @SerializedName("id"     ) var id     : Int    = 0,
        @SerializedName("tittle" ) var tittle : String = "null",
        @SerializedName("data"   ) var data   : String = "null",
        @SerializedName("type"   ) var type   : String = "null"

)