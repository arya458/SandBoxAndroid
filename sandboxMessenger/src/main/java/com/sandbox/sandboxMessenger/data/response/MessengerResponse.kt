package com.sandbox.sandboxMessenger.data.response

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "messenger")
data class MessengerResponse(

        @PrimaryKey
        var id :Int = 0,
        var userId: String="",
//        var pass: String="",
        var roomAlias: String="",
        var roomId: String="",
        var token: String=""
)
