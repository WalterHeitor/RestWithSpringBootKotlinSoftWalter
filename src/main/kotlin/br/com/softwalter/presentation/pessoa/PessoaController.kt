package br.com.softwalter.presentation.pessoa

import br.com.softwalter.domain.usecase.PessoaUseCase
import br.com.softwalter.presentation.mapper.PessoaMapper
import br.com.softwalter.presentation.pessoa.dto.v1.PessoaRequest
import br.com.softwalter.presentation.pessoa.dto.v1.PessoaResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
@Controller
@RestController
@RequestMapping("/cadastro/v1/pessoas")
@Tag(name = "Pessoas", description = "Endpoints para cadastro de pessoas")
class PessoaController(
    val pessoaUseCase: PessoaUseCase,
    val pessoaMapper: PessoaMapper
) {

    @CrossOrigin(origins = ["http://localhost:8080"])
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
        produces = [MediaType.APPLICATION_JSON_VALUE])
    @Operation(
        summary = "busca pessoas",
        description = "Busca todas pessoas cadastradas no banco",
        tags = ["Pessoas"],
        responses = [
            ApiResponse(
                description = "Success",
                responseCode = "200",
                content = [
                    Content(array = ArraySchema( schema = Schema(implementation = PessoaResponse::class)))
                ]
            ),
            ApiResponse(description = "No Content", responseCode = "204", content = [
                Content(schema = Schema(implementation = UInt::class))
            ]),
        ]
    )
    fun buscarListPessoa(): ResponseEntity<List<PessoaResponse>> {
        return ResponseEntity.ok(pessoaUseCase.buscarPessoas())
    }

    @CrossOrigin(origins = ["http://localhost:8080", "https://softWalter.com.br"])
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

    @PatchMapping(
        value = ["/{id_pessoa}"],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun desabilitarPessoaPorId(
        @PathVariable(value = "id_pessoa") idPessoa: Long
    ): ResponseEntity<PessoaResponse?> {
        return ResponseEntity.ok(pessoaUseCase.desbilitarPessoaPorId(idPessoa))
    }
}