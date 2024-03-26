package com.sandbox.sandboxMessenger.data.response.RegisterStepsResponse

import com.google.gson.annotations.SerializedName


data class Policies (

  @SerializedName("privacy_policy" ) var privacyPolicy : PrivacyPolicy? = PrivacyPolicy()

)