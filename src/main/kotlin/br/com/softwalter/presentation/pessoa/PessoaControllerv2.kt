package br.com.softwalter.presentation.pessoa

import br.com.softwalter.domain.usecase.PessoaUseCase
import br.com.softwalter.presentation.mapper.PessoaMapper
import br.com.softwalter.presentation.pessoa.dto.v1.PessoaResponse
import br.com.softwalter.presentation.pessoa.dto.v2.PessoaRequest
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
@Controller
@RestController
@RequestMapping("/cadastro/v2")
class PessoaControllerv2(
    val pessoaUseCase: PessoaUseCase,
    val pessoaMapper: PessoaMapper
) {

    @PostMapping(
        value = ["pessoas"],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun salvarPessoa(
        @RequestBody pessoaRequest: PessoaRequest
    ): PessoaResponse? {
        val pessoa = pessoaMapper.pessoaRequestToPessoav2(pessoaRequest)
        return pessoaUseCase.salvarPessoa(pessoa)
    }
}