package br.com.softwalter.presentation.pessoa

import br.com.softwalter.domain.usecase.v1.PessoaUseCase
import br.com.softwalter.presentation.mapper.PessoaMapper
import br.com.softwalter.presentation.pessoa.dto.PessoaRequest
import br.com.softwalter.presentation.pessoa.dto.PessoaResponse
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
@Controller
@RestController("/v1/cadastro")
class PessoaController(
    val pessoaUseCase: PessoaUseCase,
    val pessoaMapper: PessoaMapper
) {

    @RequestMapping(
        value = ["/pessoas/{id_pessoa}"],
        method = [RequestMethod.GET],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun buscarPessoa(
        @PathVariable(value = "id_pessoa") idPessoa: Long
    ): PessoaResponse? {
        return pessoaUseCase.buscarPessoaPorId(idPessoa)
    }

    @RequestMapping(
        value = ["pessoa"],
        method = [RequestMethod.POST],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun salvarPessoa(
        @RequestBody pessoaRequest: PessoaRequest
    ): PessoaResponse? {
        val pessoa = pessoaMapper.pessoaRequestToPessoa(pessoaRequest)
        return pessoaUseCase.salvarPessoa(pessoa)
    }
}