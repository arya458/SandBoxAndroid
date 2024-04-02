package com.arya.danesh.myresume.data.response

import com.google.gson.annotations.SerializedName


data class SkillResponse (

        @SerializedName("key"    ) var key    : String           = "null",
        @SerializedName("skills" ) var skills : ArrayList<Skills> = arrayListOf()

)
