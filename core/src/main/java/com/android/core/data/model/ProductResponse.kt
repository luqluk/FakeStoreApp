package com.android.core.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ProductResponse(
    val id: Int? = 0,
    val title: String? = "",
    val price: Double? = 0.0,
    val description: String? = "",
    val category: String? = "",
    val image: String? = "",
    val rating: Rating? = null
) {
    @Serializable
    data class Rating(
        val rate: Double? = 0.0,
        val count: Int? = 0
    )
}



