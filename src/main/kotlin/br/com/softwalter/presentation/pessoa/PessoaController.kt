package br.com.softwalter.presentation.pessoa

import br.com.softwalter.domain.usecase.v1.PessoaUseCase
import br.com.softwalter.presentation.pessoa.dto.PessoaResponse
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController("v1/cadastro")
class PessoaController (val pessoaUseCase: PessoaUseCase) {

    @RequestMapping(value = ["pessoas/{id_pessoa}"], method = [RequestMethod.GET])
    fun buscarPessoa(
        @PathVariable(value = "id_proposta") idPessoa: Long
    ) : PessoaResponse? {
        return pessoaUseCase.buscarPessoaPorId(idPessoa)
    }
}