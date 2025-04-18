package com.android.core.data.service

import com.android.core.data.model.LoginResponse
import com.android.core.data.model.ProductResponse
import com.android.core.domain.model.LoginBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface FakeStoreService {
    @POST("auth/login")
    suspend fun login(
        @Body loginBody: LoginBody
    ): Response<LoginResponse>

    @GET("products")
    suspend fun getProducts(): Response<List<ProductResponse>>
}