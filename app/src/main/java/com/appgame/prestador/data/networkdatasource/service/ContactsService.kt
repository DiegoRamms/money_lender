package com.appgame.prestador.data.networkdatasource.service

import com.appgame.prestador.domain.contact.*
import retrofit2.http.Body
import retrofit2.http.POST

interface ContactsService {
    @POST("contact/getContacts")
    suspend fun getContacts(): ContactsResponse

    @POST("contact/add")
    suspend fun addContact(@Body addContactRequest: AddContactRequest): ContactResponse

    @POST("contact/listPending")
    suspend fun getPendingContacts(): ContactsResponse

    @POST("contact/cancelRequest")
    suspend fun deleteContactRequest(@Body contactIdRequest: ContactIdRequest): ContactResponse

    @POST("contact/listToAccept")
    suspend fun getContactsToAccept(): ContactsResponse

    @POST("contact/accept")
    suspend fun acceptContact(@Body contactIdRequest: ContactIdRequest): ContactResponse
}
