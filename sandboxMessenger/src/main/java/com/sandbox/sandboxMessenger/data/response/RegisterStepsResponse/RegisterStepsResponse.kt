package com.sandbox.sandboxMessenger.data.response.RegisterStepsResponse

import com.google.gson.annotations.SerializedName


data class RegisterStepsResponse (

        @SerializedName("m.login.recaptcha" ) var recaptcha : recaptcha? = recaptcha(),
        @SerializedName("m.login.terms"     ) var terms     : terms?     = terms()

)