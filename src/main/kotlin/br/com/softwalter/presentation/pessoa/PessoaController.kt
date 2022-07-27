package br.com.softwalter.presentation.pessoa

import br.com.softwalter.domain.usecase.v1.PessoaUseCase
import br.com.softwalter.presentation.mapper.PessoaMapper
import br.com.softwalter.presentation.pessoa.dto.v1.PessoaRequest
import br.com.softwalter.presentation.pessoa.dto.v1.PessoaResponse
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
@Controller
@RestController
@RequestMapping("/cadastro/v1")
class PessoaController(
    val pessoaUseCase: PessoaUseCase,
    val pessoaMapper: PessoaMapper
) {

    @GetMapping(
        value = ["/pessoas/{id_pessoa}"],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun buscarPessoa(
        @PathVariable(value = "id_pessoa") idPessoa: Long
    ): PessoaResponse? {
        return pessoaUseCase.buscarPessoaPorId(idPessoa)
    }

    @PostMapping(
        value = ["pessoas"],
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