package com.appgame.prestador.data.repository

import com.appgame.prestador.data.networkdatasource.ContactsNetworkDataSource
import com.appgame.prestador.domain.BaseResult
import com.appgame.prestador.domain.contact.*
import com.appgame.prestador.domain.repository.ContactsRepository

class ContactsRepositoryImp(private val contactsNetworkDataSource: ContactsNetworkDataSource) :
    ContactsRepository {
    override suspend fun getContacts(): BaseResult<List<Contact>> {
        val response = contactsNetworkDataSource.getContacts()

        return try {
            BaseResult.resultOK(response.msg,ContactDTOMapper.mapListToDomainModel(response.contacts))
        }catch (e: java.lang.Exception){
            BaseResult.resultBad(response.msg)
        }
    }

    override suspend fun addContact(addContactRequest: AddContactRequest): BaseResult<Contact> {
        val response = contactsNetworkDataSource.addContact(addContactRequest)
        return try {
            val contact = response.contact?.let {ContactDTOMapper.mapToDomainModel(it) }
            BaseResult.resultOK(response.msg,contact,response.code)
        }catch (e: Exception){
            BaseResult.resultBad(response.msg)
        }
    }

    override suspend fun getPendingContacts(): BaseResult<List<Contact>> {
        val response = contactsNetworkDataSource.getPendingContacts()
        return try {
            val contacts = ContactDTOMapper.mapListToDomainModel(response.contacts)
            BaseResult.resultOK(response.msg,contacts,response.code)
        }catch (e: Exception){
            BaseResult.resultBad(response.msg)
        }
    }

    override suspend fun deleteContactRequest(idContactRequest: IdContactRequest): BaseResult<Contact> {
        val response = contactsNetworkDataSource.deleteContactRequest(idContactRequest)
        return try {
            BaseResult.resultOK(response.msg,null,response.code)
        }catch (e: Exception){
            BaseResult.resultBad(response.msg)
        }
    }

    override suspend fun getContactsToAccept(): BaseResult<List<Contact>> {
        val response = contactsNetworkDataSource.getContactsToAccept()

        return try {
            BaseResult.resultOK(response.msg,ContactDTOMapper.mapListToDomainModel(response.contacts))
        }catch (e: java.lang.Exception){
            BaseResult.resultBad(response.msg)
        }
    }

    override suspend fun acceptContact(idContactRequest: IdContactRequest): BaseResult<Contact> {
        val response = contactsNetworkDataSource.acceptContact(idContactRequest)
        return try {
            val contact = response.contact?.let { ContactDTOMapper.mapToDomainModel(it) }
            BaseResult.resultOK(response.msg,contact,response.code)
        }catch (e: Exception){
            BaseResult.resultBad(response.msg)
        }
    }
}