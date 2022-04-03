package com.appgame.prestador.model.user

import com.appgame.prestador.model.Mapper

object UserDTOMapper : Mapper<UserDTO, User> {
    override fun mapToDomainModel(model: UserDTO): User {
        return User(model.name, model.email,model.code, model.createdAt, model.uid)
    }

    override fun mapFromDomainModel(domainModel: User): UserDTO {
        return UserDTO(
            domainModel.name,
            domainModel.email,
            domainModel.code,
            domainModel.createdAt,
            domainModel.uid
        )
    }
}