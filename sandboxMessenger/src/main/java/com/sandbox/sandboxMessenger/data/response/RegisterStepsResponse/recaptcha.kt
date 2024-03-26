package com.sandbox.sandboxMessenger.data.response.RegisterStepsResponse

import com.google.gson.annotations.SerializedName


data class recaptcha (

  @SerializedName("public_key" ) var publicKey : String? = null

)