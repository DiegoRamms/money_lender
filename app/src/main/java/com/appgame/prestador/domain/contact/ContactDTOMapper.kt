package com.appgame.prestador.domain.contact

import com.appgame.prestador.domain.Mapper

object ContactDTOMapper : Mapper<ContactDTO, Contact> {
    override fun mapToDomainModel(model: ContactDTO): Contact {
        return Contact(model.code, model.email, model.name, model.idContact)
    }

    override fun mapFromDomainModel(domainModel: Contact): ContactDTO {
        return ContactDTO(domainModel.code, domainModel.email, domainModel.name,domainModel.idContact)
    }

    fun mapListToDomainModel(contactsDTO: List<ContactDTO>): List<Contact>{
        return contactsDTO.map { mapToDomainModel(it) }
    }
}