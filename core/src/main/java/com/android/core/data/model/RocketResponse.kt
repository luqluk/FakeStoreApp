package com.android.core.data.model

import kotlinx.serialization.Serializable

@Serializable
data class LaunchInfoResponse(
    val auto_update: Boolean = false,
    val capsules: List<String>,
    val cores: List<Core>,
    val crew: List<Crew>,
    val date_local: String = "",
    val date_precision: String = "",
    val date_unix: Int = 0,
    val date_utc: String = "",
    val details: String? = "",
    val failures: List<Failure>,
    val fairings: Fairings? = null,
    val flight_number: Int = 0,
    val id: String = "",
    val launch_library_id: String = "",
    val launchpad: String = "",
    val links: Links,
    val name: String = "",
    val net: Boolean = false,
    val payloads: List<String>,
    val rocket: String = "",
    val ships: List<String>,
    val static_fire_date_unix: Int = 0,
    val static_fire_date_utc: String = "",
    val success: Boolean = false,
    val tbd: Boolean = false,
    val upcoming: Boolean = false,
    val window: Int = 0
)

@Serializable
data class Core(
    val core: String = "",
    val flight: Int = 0,
    val gridfins: Boolean = false,
    val landing_attempt: Boolean = false,
    val landing_success: Boolean = false,
    val landing_type: String = "",
    val landpad: String = "",
    val legs: Boolean = false,
    val reused: Boolean = false
)

@Serializable
data class Crew(
    val crew: String = "",
    val role: String = ""
)

@Serializable
data class Failure(
    val altitude: Int = 0,
    val reason: String = "",
    val time: Int = 0
)

@Serializable
data class Fairings(
    val recovered: Boolean = false,
    val recovery_attempt: Boolean = false,
    val reused: Boolean = false,
    val ships: List<String>
)

@Serializable
data class Links(
    val article: String = "",
    val flickr: Flickr? = null,
    val patch: Patch? = null,
    val presskit: String = "",
    val reddit: Reddit? = null,
    val webcast: String = "",
    val wikipedia: String = "",
    val youtube_id: String = ""
)

@Serializable
data class Flickr(
    val original: List<String>,
    val small: List<String>
)

@Serializable
data class Patch(
    val large: String = "",
    val small: String = ""
)

@Serializable
data class Reddit(
    val campaign: String = "",
    val launch: String = "",
    val media: String = "",
    val recovery: String = ""
)




