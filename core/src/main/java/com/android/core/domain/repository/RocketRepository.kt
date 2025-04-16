package com.android.core.domain.repository

import com.android.core.data.model.LaunchInfoResponse
import com.android.core.util.resource.ResourceCore

interface RocketRepository {
    suspend fun fetchAllLaunches(): ResourceCore<List<LaunchInfoResponse>>
}