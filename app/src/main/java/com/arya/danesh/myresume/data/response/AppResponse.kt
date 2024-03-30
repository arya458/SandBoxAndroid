package com.arya.danesh.myresume.data.response

data class AppResponse(

        val key: String,
        val apps: List<Apps>

)

data class Apps(

        val id: Int,
        val name: String,
        val logo: String,
        val detail: String,
        val date: Int,
        val skills: List<Skills>,
        val link: String,
        )

data class Skills(

        val id: Int,
        val tittle: String,
        val text: String,
        val logo: String,
        val percentage: Int,

        )
