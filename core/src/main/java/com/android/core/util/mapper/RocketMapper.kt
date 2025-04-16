package com.android.core.util.mapper

import com.android.core.data.model.LaunchInfoResponse
import com.android.core.domain.model.RocketDomainModel

fun LaunchInfoResponse.toRocketDomainModel() = RocketDomainModel(
    date_local = date_local,
    details = details ?: "",
    flight_number = flight_number,
    id = id,
    logo = links.patch?.small ?: "",
    name = name,
    success = success,
)

fun List<LaunchInfoResponse>.toRocketListDomainModel(): List<RocketDomainModel> = this.map { it.toRocketDomainModel() }