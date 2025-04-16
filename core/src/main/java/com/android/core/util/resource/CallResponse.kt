package com.android.core.util.resource

import retrofit2.Response
import java.net.UnknownHostException

suspend fun <T, M> callResponse(
    call: suspend () -> Response<T>,
    mapData: (T) -> M
): ResourceCore<M> {
    return try {
        val response = call.invoke()
        return if(response.isSuccessful) {
            response.body()?.let {
                ResourceCore(
                    resourceState = ResourceState.Success,
                    data = mapData(it)
                )
            } ?: run {
                ResourceCore(ResourceState.HttpErrors.ResponseEmpty(response.message()), null)
            }
        } else {
            ResourceCore(
                resourceState = when(response.code()) {
                    403 -> ResourceState.HttpErrors.Forbidden(response.message())
                    404 -> ResourceState.HttpErrors.NotFound(response.message())
                    500 -> ResourceState.HttpErrors.InternalServerError(response.message())
                    502 -> ResourceState.HttpErrors.BadGateWay(response.message())
                    301 -> ResourceState.HttpErrors.Removed(response.message())
                    302 -> ResourceState.HttpErrors.RemovedResourceFoundState(response.message())
                    else -> ResourceState.Error.GeneralError(response.message())
                },
                data = null
            )
        }
    } catch (ex: UnknownHostException) {
        ResourceCore(ResourceState.Error.UnknownHost(ex.message.toString()), null)
    } catch (ex: Exception) {
        ResourceCore(ResourceState.Error.GeneralError(ex.message.toString()), null)
    }
}