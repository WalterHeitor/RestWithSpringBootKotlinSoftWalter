package br.com.softwalter.domain.usecase.impl

import br.com.softwalter.domain.model.Pessoa
import br.com.softwalter.domain.repository.PessoaRepository
import br.com.softwalter.domain.usecase.PessoaUseCase
import br.com.softwalter.exceptions.PessoaNullException
import br.com.softwalter.presentation.mapper.PessoaMapper
import br.com.softwalter.presentation.pessoa.PessoaController
import br.com.softwalter.presentation.pessoa.dto.v1.PessoaResponse
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.logging.Logger
import kotlin.collections.ArrayList

@Service
class PessoaUseCaseImpl(
    val pessoaMapper: PessoaMapper,
    val  pessoaRepository: PessoaRepository
    ) : PessoaUseCase {
    private val logger = Logger.getLogger(PessoaUseCaseImpl::class.java.name)

    override fun buscarPessoaPorId(idPessoa: Long): PessoaResponse? {

        val pessoa: Pessoa? = buscandoPessoa(idPessoa)
        logger.info("usecase - pessoa encontrada no Banco de Dados ...")
        val pessoaResponse: PessoaResponse =
            pessoaMapper.pessoaToPessoaResponse(pessoa!!)
        adicionadoHateoas(pessoaResponse)

        return pessoaResponse
    }

    override fun salvarPessoa(pessoa: Pessoa?): PessoaResponse? {
        if (pessoa == null) throw PessoaNullException()
        logger.info("usecase - Salvando pessoa no Banco de Dados ...")
        val pessoaResp: Pessoa = pessoaRepository.save(pessoa)
        logger.info("usecase - pessoa salva com sucesso no Banco de Dados ...")
        val pessoaResponse: PessoaResponse =
            pessoaMapper.pessoaToPessoaResponse(pessoaResp)
        adicionadoHateoas(pessoaResponse)
        return pessoaResponse
    }

    override fun buscarPessoas(): List<PessoaResponse> {
        logger.info("usecase - buscando pessoas no Banco de Dados ...")
        val pessoas: MutableList<Pessoa> = pessoaRepository.findAll()
        logger.info("usecase - busca de pessoas no Banco de Dados ...")
        val pessoasResponse: List<PessoaResponse> =  pessoaMapper.pessoasToListResponse(pessoas)
        for (response in pessoasResponse) {
            adicionadoHateoas(response)
        }
        return pessoasResponse
    }

    override fun atualizarPessoa(idPessoa: Long): PessoaResponse? {
        val pessoa: Pessoa? = buscandoPessoa(idPessoa)
        val pessoaPersist = pessoaRepository.save(pessoa!!)
        logger.info("usecase - pessoa atualizada com sucesso no Banco de Dados ...")
        return pessoaPersist.let { pessoaMapper.pessoaToPessoaResponse(it) }
    }

    @Transactional
    override fun desbilitarPessoaPorId(idPessoa: Long): PessoaResponse? {

        val pessoa: Pessoa? = buscandoPessoa(idPessoa)
        logger.info("desabilitando pessoa no Banco de Dados ... ")
        pessoaRepository.desabilitarPessoa(idPessoa)
        logger.info("usecase - pessoa encontrada no Banco de Dados ...")
        val pessoaResponse: PessoaResponse =
            pessoaMapper.pessoaToPessoaResponse(pessoa!!)
        adicionadoHateoas(pessoaResponse)

        return pessoaResponse
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