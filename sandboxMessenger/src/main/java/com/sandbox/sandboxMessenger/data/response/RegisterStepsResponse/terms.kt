package com.sandbox.sandboxMessenger.data.response.RegisterStepsResponse

import com.google.gson.annotations.SerializedName

data class terms (

  @SerializedName("policies" ) var policies : Policies? = Policies()

)