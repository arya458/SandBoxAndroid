package com.arya.danesh.myresume.data.response

import com.google.gson.annotations.SerializedName


data class AppResponse (

        @SerializedName("key"  ) var key  : String         = "null",
        @SerializedName("apps" ) var apps : ArrayList<Apps> = arrayListOf()

)
data class Apps (

        @SerializedName("id"     ) var id     : Int              = 0,
        @SerializedName("name"   ) var name   : String          = "null",
        @SerializedName("logo"   ) var logo   : String           = "null",
        @SerializedName("detail" ) var detail : String           = "null",
        @SerializedName("date"   ) var date   : Int             = 0,
        @SerializedName("skills" ) var skills : ArrayList<Skills> = arrayListOf(),
        @SerializedName("link"   ) var link   : String           = "null"

)

data class Skills (

        @SerializedName("id"         ) var id         : Int    = 0,
        @SerializedName("tittle"     ) var tittle     : String = "null",
        @SerializedName("text"       ) var text       : String = "null",
        @SerializedName("logo"       ) var logo       : String = "null",
        @SerializedName("percentage" ) var percentage : Int    = 0

)
