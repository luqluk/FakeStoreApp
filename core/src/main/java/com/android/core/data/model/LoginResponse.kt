package com.android.core.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class LoginResponse(
    @field:SerializedName("token")
    val token: String? = ""
)
