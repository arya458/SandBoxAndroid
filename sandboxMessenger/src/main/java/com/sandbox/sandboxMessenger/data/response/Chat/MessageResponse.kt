package com.sandbox.sandboxMessenger.data.response.Chat

data class MessageResponse(

        val sender :String,
        val text : String,
        val isLastMessageSenderTheSame :Boolean

)
