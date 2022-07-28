package br.com.softwalter.domain.usecase.v1.impl

import br.com.softwalter.domain.model.Pessoa
import br.com.softwalter.domain.repository.PessoaRepository
import br.com.softwalter.domain.usecase.v1.PessoaUseCase
import br.com.softwalter.presentation.mapper.PessoaMapper
import br.com.softwalter.presentation.pessoa.PessoaController
import br.com.softwalter.presentation.pessoa.dto.v1.PessoaResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.stereotype.Service
import java.util.logging.Logger
import kotlin.collections.ArrayList

@Service
class PessoaUseCaseImpl(
    val pessoaMapper: PessoaMapper,

    ) : PessoaUseCase {
    private val logger = Logger.getLogger(PessoaUseCaseImpl::class.java.name)
    @Autowired
    private lateinit var  pessoaRepository: PessoaRepository
    override fun buscarPessoaPorId(idPessoa: Long): PessoaResponse? {
        val pessoa: Pessoa? = buscandoPessoa(idPessoa)

        logger.info("usecase - pessoa encontrada no Banco de Dados ...")

        val pessoaResponse: PessoaResponse =
            pessoaMapper.pessoaToPessoaResponse(pessoa!!)
        adicionadoHateoas(pessoaResponse)
        return pessoaResponse
    }

    override fun buscarPessoas(): List<PessoaResponse> {
        logger.info("usecase - buscando pessoa no Banco de Dados ...")
        val pessoas: MutableList<Pessoa> = ArrayList()
        val responsesPessoas = pessoaMapper.listPessoaToResponsesPessoas(pessoas)
        for (response in responsesPessoas) {
            adicionadoHateoas(response)
        }
        return responsesPessoas
    }

    override fun salvarPessoa(pessoa: Pessoa): PessoaResponse? {
        logger.info("usecase - Salvando pessoa no Banco de Dados ...")
        val pessoaResp: Pessoa = pessoaRepository.save(pessoa)
        logger.info("usecase - pessoa salva com sucesso no Banco de Dados ...")
        val pessoaResponse: PessoaResponse =
            pessoaMapper.pessoaToPessoaResponse(pessoaResp)
        adicionadoHateoas(pessoaResponse)
        return pessoaResponse
    }

    override fun buscarPessoa(): MutableList<PessoaResponse> {
        logger.info("usecase - buscando pessoas no Banco de Dados ...")
        val pessoas: MutableList<Pessoa> = pessoaRepository.findAll()
        logger.info("usecase - busca de pessoas no Banco de Dados ...")
        val pessoasResponse: MutableList<PessoaResponse>  =  pessoaMapper.pessoasToListResponse(pessoas)
        for (response in pessoasResponse) {
            adicionadoHateoas(response)
        }
        return pessoasResponse
    }

    override fun atualizarPessoa(idPessoa: Long): PessoaResponse? {
        val pessoa: Pessoa? = buscandoPessoa(idPessoa)
        return pessoa?.let { pessoaMapper.pessoaToPessoaResponse(it) }
    }

    private fun buscandoPessoa(idPessoa: Long): Pessoa? {
        logger.info("usecase - buscando pessoa no Banco de Dados ...")
        val pessoa: Pessoa? = pessoaRepository.findById(idPessoa)
            .orElseThrow { RuntimeException("lancar exectipito banco") }
        return pessoa
    }

    private fun adicionadoHateoas(pessoaResponse: PessoaResponse) {
        val withSeltRel = linkTo(PessoaController::class.java)
            .slash(pessoaResponse.idPessoa).withSelfRel()
        pessoaResponse.add(withSeltRel)
    }
}