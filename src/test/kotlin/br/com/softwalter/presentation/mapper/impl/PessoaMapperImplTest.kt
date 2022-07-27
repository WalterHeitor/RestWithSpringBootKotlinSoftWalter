package br.com.softwalter.presentation.mapper.impl

import br.com.softwalter.domain.model.Pessoa
import br.com.softwalter.presentation.mapper.PessoaMapper
import br.com.softwalter.presentation.pessoa.dto.v1.PessoaResponse
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class PessoaMapperImplTest{

    val pessoaMapper: PessoaMapper = PessoaMapperImpl()

    @Test
    fun mapperPessoa () {

        //input
        val pessoa = Pessoa(1, "Walter", "walter@email")
        //output
        val expected: PessoaResponse = PessoaResponse(1, "Walter", "walter@email")
        //execute
        val actual: PessoaResponse = pessoaMapper.pessoaToPessoaResponse(pessoa)
        //validation
        Assertions.assertEquals(expected, actual)
    }
}