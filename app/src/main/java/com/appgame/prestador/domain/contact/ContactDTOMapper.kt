package com.appgame.prestador.domain.contact

import com.appgame.prestador.domain.Mapper

object ContactDTOMapper : Mapper<ContactDTO, Contact> {
    override fun mapToDomainModel(model: ContactDTO): Contact {
        return Contact(model.userID,model.code, model.email, model.name, model.contactId)
    }

    override fun mapFromDomainModel(domainModel: Contact): ContactDTO {
        return ContactDTO(domainModel.userId,domainModel.code, domainModel.email, domainModel.name,domainModel.contactId)
    }

    fun mapListToDomainModel(contactsDTO: List<ContactDTO>): List<Contact>{
        return contactsDTO.map { mapToDomainModel(it) }
    }
}