package com.android.core.util.mapper

import com.android.core.data.model.ProductResponse
import com.android.core.database.product.ProductEntity
import com.android.core.domain.model.ListProductDomainModel
import com.android.core.domain.model.ProductDomainModel


internal fun List<ProductResponse>?.toListProductDomainModel() = this?.run {
    return ListProductDomainModel(
        product = map { it.toProductDomainModel() }
    )
} ?: ListProductDomainModel()


internal fun ProductResponse?.toProductDomainModel() = this?.run {
    return ProductDomainModel(
        id = id ?: 0,
        title = title.orEmpty(),
        price = price ?: 0.0,
        description = description.orEmpty(),
        category = category.orEmpty(),
        image = image.orEmpty(),
        rate = rating?.rate ?: 0.0,
        count = rating?.count ?: 0
    )
} ?: ProductDomainModel()

fun ProductDomainModel.asProductEntity(): ProductEntity {
    return ProductEntity(
        id = id,
        title = title,
        price = price,
        description = description,
        category = category,
        image = image,
        rate = rate ?: 0.0,
        count = count ?: 0,
        piece = piece ?: 1
    )
}