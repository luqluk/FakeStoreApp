package com.android.core.data.service

import com.android.core.data.model.LaunchInfoResponse
import retrofit2.Response
import retrofit2.http.GET

interface RocketService {
    @GET("v5/launches")
    suspend fun getAllLaunches(
    ): Response<List<LaunchInfoResponse>>
}