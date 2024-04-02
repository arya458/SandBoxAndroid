package com.arya.danesh.myresume.data.response

import com.google.gson.annotations.SerializedName


data class BlogResponse (

        @SerializedName("key"  ) var key  : String         = "null",
        @SerializedName("blog" ) var blog : ArrayList<BlogPost> = arrayListOf()

)
data class BlogPost (

        @SerializedName("id"     ) var id     : Int    = 0,
        @SerializedName("tittle" ) var tittle : String = "null",
        @SerializedName("image"  ) var image  : String = "null",
        @SerializedName("detail" ) var detail : String = "null",
        @SerializedName("date"   ) var date   : Int    = 0

)
