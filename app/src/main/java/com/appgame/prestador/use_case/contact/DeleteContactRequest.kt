package com.appgame.prestador.use_case.contact

import com.appgame.prestador.domain.BaseResult
import com.appgame.prestador.domain.contact.Contact
import com.appgame.prestador.domain.contact.DeleteContactRequestRequest
import com.appgame.prestador.domain.repository.ContactsRepository

class DeleteContactRequest(private val repository: ContactsRepository) {

    suspend operator fun invoke(deleteContactRequest: DeleteContactRequestRequest): BaseResult<Contact>{
        return repository.deleteContactRequest(deleteContactRequest)
    }

}