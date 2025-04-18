package com.android.core.domain.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
data class ListProductDomainModel(
    val product: List<ProductDomainModel> = emptyList()
)

@Keep
@Parcelize
data class ProductDomainModel(
    val id: Int = 0,
    val title: String = "",
    val price: Double = 0.0,
    val description: String = "",
    val category: String = "",
    val image: String = "",
    val rate: Double? = 0.0,
    val count: Int? = 0,
    val piece: Int? = 1,
) : Parcelable