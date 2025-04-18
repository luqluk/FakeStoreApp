package com.android.core.data.repository

import com.android.core.data.model.LoginResponse
import com.android.core.data.service.FakeStoreService
import com.android.core.domain.model.ListProductDomainModel
import com.android.core.domain.model.LoginBody
import com.android.core.domain.repository.MainRepository
import com.android.core.util.mapper.toListProductDomainModel
import com.android.core.util.resource.ResourceCore
import com.android.core.util.resource.callResponse

class MainRepositoryImpl(
    private val service: FakeStoreService
): MainRepository {
    override suspend fun login(login: LoginBody): ResourceCore<LoginResponse> = callResponse(
        call = {
            service.login(login)
        },
        mapData = {
            it
        }
    )
    override suspend fun getProducts(): ResourceCore<ListProductDomainModel> = callResponse(
        call = {
            service.getProducts()
        },
        mapData = {
            it.toListProductDomainModel()
        }
    )
//
//    override suspend fun getSender(): ResourceCore<ListSenderDomainModel> = callResponse(
//        call = {
//            service.getAllSenders()
//        },
//        mapData = {
//            it.toListSenderDomainModel()
//        }
//    )
//
//    override suspend fun getDestination(): ResourceCore<ListDestinationDomainModel> = callResponse(
//        call = {
//            service.getAllDestinations()
//        },
//        mapData = {
//            it.toListDestinationDomainModel()
//        }
//    )
//
//    override suspend fun postLetterNumber(
//        data: LetterRequestBody,
//        file: MultipartBody.Part?
//    ): ResourceCore<LetterDomainModel> = callResponse(
//        call = {
//            service.postLetterNumber(
//                data.type,
//                data.number,
//                data.senderId,
//                data.destinationId,
//                data.destinationName,
//                data.note,
//                file
//            )
//        },
//        mapData = {
//            it.toDataSearchModel()
//        }
//    )
//
//    override suspend fun postLetterFile(
//        id: Int?,
//        file: MultipartBody.Part?
//    ): ResourceCore<LetterDomainModel> = callResponse(
//        call = {
//            service.uploadFileSurat(id, file)
//        },
//        mapData = {
//            it.toDataSearchModel()
//        }
//    )
}