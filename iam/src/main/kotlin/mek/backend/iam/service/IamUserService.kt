package mek.backend.iam.service

import mek.backend.auth.model.entity.UserEntity
import mek.backend.iam.entity.IamUserEntity
import mek.backend.iam.repository.IamUserRepository
import org.springframework.stereotype.Service

@Service
class IamUserService(private val iamUserRepository: IamUserRepository) {

    fun getAllUsers(): List<IamUserEntity> {
        return iamUserRepository.getAllUsers();
    }
}