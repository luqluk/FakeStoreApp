package com.android.core.data.repository

import com.android.core.data.model.LaunchInfoResponse
import com.android.core.data.service.RocketService
import com.android.core.domain.repository.RocketRepository
import com.android.core.util.resource.ResourceCore
import com.android.core.util.resource.callResponse

class RocketRepositoryImpl(
    private val service: RocketService
): RocketRepository {
    override suspend fun fetchAllLaunches(): ResourceCore<List<LaunchInfoResponse>> = callResponse(
        call = {
            service.getAllLaunches()
        },
        mapData = {
            it
        }
    )
}