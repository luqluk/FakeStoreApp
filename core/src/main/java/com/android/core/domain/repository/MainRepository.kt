package com.android.core.domain.repository

import com.android.core.data.model.LoginResponse
import com.android.core.domain.model.ListProductDomainModel
import com.android.core.domain.model.LoginBody
import com.android.core.util.resource.ResourceCore


interface MainRepository {
    suspend fun login(login: LoginBody): ResourceCore<LoginResponse>
    suspend fun getProducts(): ResourceCore<ListProductDomainModel>
}