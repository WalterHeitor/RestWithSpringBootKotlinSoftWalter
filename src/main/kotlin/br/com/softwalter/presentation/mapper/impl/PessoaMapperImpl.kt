package br.com.softwalter.presentation.mapper.impl

import br.com.softwalter.domain.model.Pessoa
import br.com.softwalter.presentation.mapper.PessoaMapper
import br.com.softwalter.presentation.pessoa.dto.v1.PessoaRequest
import br.com.softwalter.presentation.pessoa.dto.v1.PessoaResponse
import org.springframework.data.domain.Page
import org.springframework.stereotype.Component
import java.time.Instant
import java.util.Date

@Component
class PessoaMapperImpl : PessoaMapper {

    override fun pessoaToPessoaResponse(pessoa: Pessoa): PessoaResponse {

        return PessoaResponse(
            pessoa.idPessoa,
            pessoa.nome,
            pessoa.email,
            pessoa.ativo
        )
    }

    override fun pessoaRequestToPessoa(pessoaRequest: PessoaRequest): Pessoa {
        return  Pessoa(
            nome = pessoaRequest.nome,
            email = pessoaRequest.email,
            cpf = "",
            dataNascimento = Date.from(Instant.now(),).toString()
        )
    }

    override fun pessoaRequestToPessoav2(pessoaRequest: br.com.softwalter.presentation.pessoa.dto.v2.PessoaRequest): Pessoa {
        return  Pessoa(
            nome = pessoaRequest.nome,
            email = pessoaRequest.email,
            cpf = pessoaRequest.cpf,
            dataNascimento = pessoaRequest.dataNascimento,

        )
    }

    override fun pessoasToListResponse(pessoas: Page<Pessoa>): Page<PessoaResponse> {
//        return pessoas.map { pessoa ->
//            PessoaResponse(pessoa.idPessoa, pessoa.nome, pessoa.email) }.toMutableList()
        val pessoas = pessoas.map { pessoa: Pessoa ->
            PessoaResponse(pessoa.idPessoa, pessoa.nome, pessoa.email, pessoa.ativo)
        }
        return pessoas
    }
}