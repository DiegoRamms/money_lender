package com.appgame.prestador.domain.user

import com.appgame.prestador.domain.Mapper

object UserDTOMapper : Mapper<UserDTO, User> {
    override fun mapToDomainModel(model: UserDTO): User {
        return User(model.status, model.name, model.email, model.code, model.createdAt, model.uid)
    }

    override fun mapFromDomainModel(domainModel: User): UserDTO {
        return UserDTO(
            domainModel.status,
            domainModel.name,
            domainModel.email,
            domainModel.code,
            domainModel.createdAt,
            domainModel.uid
        )
    }
}