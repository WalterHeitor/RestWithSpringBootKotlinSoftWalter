package br.com.softwalter.domain.usecase.impl

import br.com.softwalter.domain.repository.rbac.UsersRepository
import br.com.softwalter.domain.usecase.UsersUseCase
import br.com.softwalter.presentation.mapper.ProdutoMapper
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class UsersUseCaseImpl(
    val produtoMapper: ProdutoMapper,
    val usersRepository: UsersRepository
) : UsersUseCase, UserDetailsService {
    private val logger = Logger.getLogger(UsersUseCaseImpl::class.java.name)

    override fun loadUserByUsername(username: String?): UserDetails {
        logger.info("usecase details - buscando por name do user no Banco de Dados ...")
        val user = usersRepository.findByUserName(username)
        return user ?: throw UsernameNotFoundException("User Name $username nao encontrado ")
    }
}