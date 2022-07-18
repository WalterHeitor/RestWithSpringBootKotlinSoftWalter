package br.com.softwalter.domain.usecase.v1.impl

import br.com.softwalter.domain.model.Pessoa
import br.com.softwalter.domain.usecase.v1.PessoaUseCase
import br.com.softwalter.presentation.mapper.PessoaMapper
import br.com.softwalter.presentation.pessoa.dto.PessoaResponse
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class PessoaUseCaseImpl(val pessoaMapper: PessoaMapper) : PessoaUseCase {
    private val logger = Logger.getLogger(PessoaUseCaseImpl::class.java.name)
    override fun buscarPessoaPorId(idPessoa: Long): PessoaResponse? {
        logger.info("buscando pessoa no Banco de Dados ...")
        var pessoa = Pessoa(1, "walter", "walter@oliveira")
        return pessoaMapper.pessoaToPessoaResponse(pessoa)
    }
}