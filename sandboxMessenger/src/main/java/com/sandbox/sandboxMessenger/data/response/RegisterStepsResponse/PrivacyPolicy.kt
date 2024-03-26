package com.sandbox.sandboxMessenger.data.response.RegisterStepsResponse

import com.google.gson.annotations.SerializedName
import com.sandbox.sandboxMessenger.data.response.RegisterStepsResponse.En


data class PrivacyPolicy (

  @SerializedName("version" ) var version : String? = null,
  @SerializedName("en"      ) var en      : En?     = En()

)