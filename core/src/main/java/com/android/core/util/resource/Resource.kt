package com.android.core.util.resource

data class ResourceCore<T>(
    val resourceState: ResourceState,
    val data: T?
)