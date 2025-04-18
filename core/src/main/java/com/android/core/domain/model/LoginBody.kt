package com.android.core.domain.model

import androidx.annotation.Keep

@Keep
data class LoginBody(
    val username: String,
    val password: String
)