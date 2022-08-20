package br.com.softwalter.domain.usecase.impl

import br.com.softwalter.domain.model.rbac.Users
import br.com.softwalter.domain.repository.rbac.UsersRepository
import br.com.softwalter.domain.usecase.ProdutoUseCase
import br.com.softwalter.domain.usecase.UsersUseCase
import br.com.softwalter.exceptions.PessoaNullException
import br.com.softwalter.presentation.mapper.ProdutoMapper
import br.com.softwalter.presentation.produto.ProdutoController
import br.com.softwalter.presentation.produto.dto.ProdutoRequest
import br.com.softwalter.presentation.produto.dto.ProdutoResponse
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class UsersUseCaseImpl(
    val produtoMapper: ProdutoMapper,
    val usersRepository: UsersRepository
) : UsersUseCase, UserDetailsService{
    private val logger = Logger.getLogger(UsersUseCaseImpl::class.java.name)

//    override fun buscarProdutoPorId(idProduto: Long): ProdutoResponse? {
//
//        val user: Users? = buscandoProduto(idProduto)
//        logger.info("usecase - user encontrada no Banco de Dados ...")
//        val produtoResponse: ProdutoResponse =
//            produtoMapper.produtoToProdutoResponse(user!!)
//        adicionadoHateoas(produtoResponse)
//
//        return produtoResponse
//    }
//
//    override fun salvarProduto(user: Users?): ProdutoResponse? {
//        if (user == null) throw PessoaNullException()
//        logger.info("usecase - Salvando user no Banco de Dados ...")
//        val produtoResp: Users = usersRepository.save(user)
//        logger.info("usecase - user salva com sucesso no Banco de Dados ...")
//        val produtoResponse: ProdutoResponse =
//            produtoMapper.produtoToProdutoResponse(produtoResp)
//        adicionadoHateoas(produtoResponse)
//        return produtoResponse
//    }
//
//    override fun buscarProdutos(): List<ProdutoResponse> {
//        logger.info("usecase - buscando produtos no Banco de Dados ...")
//        val pessoas: MutableList<Users> = usersRepository.findAll()
//        logger.info("usecase - busca de produtos no Banco de Dados ...")
//        val pessoasResponse: List<ProdutoResponse> = produtoMapper.pessoasToListResponse(pessoas)
//        for (response in pessoasResponse) {
//            adicionadoHateoas(response)
//        }
//        return pessoasResponse
//    }
//
//    override fun atualizarProduto(idProduto: Long, produtoRequest: ProdutoRequest): ProdutoResponse? {
//        val user: Users? = buscandoProduto(idProduto)
//        user!!.userName = produtoRequest.nome
//        user.descricao = produtoRequest.descricao
//        user.precoCusto = produtoRequest.precoCusto
//        user.precoVenda = produtoRequest.precoVenda
//        user.quantidade = produtoRequest.quantidade
//        user.quantidadeMinima = produtoRequest.quantidadeMinima
//        val pessoaPersist = usersRepository.save(user)
//        logger.info("usecase - user atualizada com sucesso no Banco de Dados ...")
//        return pessoaPersist.let { produtoMapper.produtoToProdutoResponse(it) }
//    }
//
//    private fun buscandoProduto(idProduto: Long): Users? {
//        logger.info("usecase - buscando user no Banco de Dados ...")
//        val user: Users? = usersRepository.findById(idProduto)
//            .orElseThrow { RuntimeException("lancar exectipito banco") }
//        return user
//    }
//
//    private fun adicionadoHateoas(produtoResponse: ProdutoResponse) {
//        val withSeltRel = linkTo(ProdutoController::class.java)
//            .slash(produtoResponse.codigoProduto).withSelfRel()
//        produtoResponse.add(withSeltRel)
//    }

    override fun loadUserByUsername(username: String?): UserDetails {
        logger.info("usecase - buscandopor name do user no Banco de Dados ...")
        val user = usersRepository.findByUserName(username)
        return user ?: throw UsernameNotFoundException("UserName $username not found ")
    }
}