package br.com.softwalter.presentation.pessoa

import br.com.softwalter.domain.usecase.v1.PessoaUseCase
import br.com.softwalter.presentation.mapper.PessoaMapper
import br.com.softwalter.presentation.pessoa.dto.v1.PessoaRequest
import br.com.softwalter.presentation.pessoa.dto.v1.PessoaResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
@Controller
@RestController
@RequestMapping("/cadastro/v1/pessoas")
class PessoaController(
    val pessoaUseCase: PessoaUseCase,
    val pessoaMapper: PessoaMapper
) {

    @GetMapping(
        value = ["/{id_pessoa}"],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun buscarPessoa(
        @PathVariable(value = "id_pessoa") idPessoa: Long
    ): ResponseEntity<PessoaResponse?> {
        return ResponseEntity.ok(pessoaUseCase.buscarPessoaPorId(idPessoa))
    }

    @GetMapping(
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun buscarListPessoa(): ResponseEntity<List<PessoaResponse>> {
        return ResponseEntity.ok(pessoaUseCase.buscarPessoas())
    }

    @PostMapping(
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun salvarPessoa(
        @RequestBody pessoaRequest: PessoaRequest
    ): ResponseEntity<PessoaResponse?> {
        val pessoa = pessoaMapper.pessoaRequestToPessoa(pessoaRequest)
        return ResponseEntity<PessoaResponse?>(pessoaUseCase.salvarPessoa(pessoa), HttpStatus.CREATED)
    }

    @PutMapping(
        value = ["/{id_pessoa}"],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun atualizarPessoa(
        @PathVariable(value = "id_pessoa") idPessoa: Long
    ): ResponseEntity<PessoaResponse?> {
        return ResponseEntity.ok(pessoaUseCase.atualizarPessoa(idPessoa))
    }
}