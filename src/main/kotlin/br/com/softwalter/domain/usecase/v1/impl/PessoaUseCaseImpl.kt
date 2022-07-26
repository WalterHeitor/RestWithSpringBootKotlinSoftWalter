package br.com.softwalter.domain.usecase.v1.impl

import br.com.softwalter.domain.model.Pessoa
import br.com.softwalter.domain.repository.PessoaRepository
import br.com.softwalter.domain.usecase.v1.PessoaUseCase
import br.com.softwalter.presentation.mapper.PessoaMapper
import br.com.softwalter.presentation.pessoa.dto.PessoaResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.logging.Logger
import kotlin.collections.ArrayList

@Service
class PessoaUseCaseImpl(
    val pessoaMapper: PessoaMapper,

    ) : PessoaUseCase {
    private val logger = Logger.getLogger(PessoaUseCaseImpl::class.java.name)
    @Autowired
    private lateinit var  pessoaRepositoryInterface: PessoaRepository
    override fun buscarPessoaPorId(idPessoa: Long): PessoaResponse? {
        logger.info("usecase - buscando pessoa no Banco de Dados ...")
        val pessoa: Pessoa? = pessoaRepositoryInterface.findById(idPessoa)
            .orElseThrow { RuntimeException("lancar exectipito banco") }

        logger.info("usecase - pessoa encontrada no Banco de Dados ...")

//        val pessoa = Pessoa(1, "walter", "walter@oliveira")
        return pessoaMapper.pessoaToPessoaResponse(pessoa!!)
    }

    override fun buscarPessoas(): List<PessoaResponse> {
        logger.info("usecase - buscando pessoa no Banco de Dados ...")
        val pessoas: MutableList<Pessoa> = ArrayList()
        return pessoaMapper.listPessoaToResponsesPessoas(pessoas)
    }

    override fun salvarPessoa(pessoa: Pessoa): PessoaResponse? {
        logger.info("usecase - Salvando pessoa no Banco de Dados ...")
        val pessoaResponse = pessoaRepositoryInterface.save(pessoa)
        logger.info("usecase - pessoa salva com sucesso no Banco de Dados ...")
        return pessoaMapper.pessoaToPessoaResponse(pessoaResponse)
    }
}