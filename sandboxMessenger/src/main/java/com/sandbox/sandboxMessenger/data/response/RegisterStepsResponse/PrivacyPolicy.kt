package com.sandbox.sandboxMessenger.data.response.RegisterStepsResponse

import com.google.gson.annotations.SerializedName


data class PrivacyPolicy (

  @SerializedName("version" ) var version : String? = null,
  @SerializedName("en"      ) var en      : En?     = En()

)