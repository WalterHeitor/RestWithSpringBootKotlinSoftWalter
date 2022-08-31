package br.com.softwalter.presentation.mapper.impl

import br.com.softwalter.domain.model.Pessoa
import br.com.softwalter.presentation.mapper.PessoaMapper
import br.com.softwalter.presentation.pessoa.dto.v1.PessoaRequest
import br.com.softwalter.presentation.pessoa.dto.v1.PessoaResponse
import org.springframework.stereotype.Component

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
        return  Pessoa(nome = pessoaRequest.nome, email = pessoaRequest.email)
    }

    override fun pessoaRequestToPessoav2(pessoaRequest: br.com.softwalter.presentation.pessoa.dto.v2.PessoaRequest): Pessoa {
        return  Pessoa(nome = pessoaRequest.nome, email = pessoaRequest.email)
    }

    override fun pessoasToListResponse(pessoas: MutableList<Pessoa>): List<PessoaResponse> {
//        return pessoas.map { pessoa ->
//            PessoaResponse(pessoa.idPessoa, pessoa.nome, pessoa.email) }.toMutableList()
        val list = pessoas.map { pessoa: Pessoa ->
            PessoaResponse(pessoa.idPessoa, pessoa.nome, pessoa.email, pessoa.ativo)
        }.toCollection(mutableListOf())
        return list
    }
}