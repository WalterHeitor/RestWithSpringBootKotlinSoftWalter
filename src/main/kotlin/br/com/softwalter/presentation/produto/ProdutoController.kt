package br.com.softwalter.presentation.produto

import br.com.softwalter.domain.model.Produto
import br.com.softwalter.domain.usecase.ProdutoUseCase
import br.com.softwalter.presentation.mapper.ProdutoMapper
import br.com.softwalter.presentation.produto.dto.ProdutoRequest
import br.com.softwalter.presentation.produto.dto.ProdutoResponse
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
@RequestMapping("/cadastro/v1/produtos")
@Tag(name = "Produtos", description = "Endpoints para cadastro de produtos")
class ProdutoController(
    val produtoUseCase: ProdutoUseCase,
    val produtoMapper: ProdutoMapper
) {

    @GetMapping(
        value = ["/{id_produto}"],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun buscarPessoa(
        @PathVariable(value = "id_produto") idProduto: Long
    ): ResponseEntity<ProdutoResponse?> {
        return ResponseEntity.ok(produtoUseCase.buscarProdutoPorId(idProduto))
    }

    @GetMapping(
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @Operation(
        summary = "busca produtos",
        description = "Busca todos produtos cadastradas no banco",
        tags = ["Pessoas"],
        responses = [
            ApiResponse(
                description = "Success",
                responseCode = "200",
                content = [
                    Content(array = ArraySchema(schema = Schema(implementation = ProdutoResponse::class)))
                ]
            ),
            ApiResponse(
                description = "No Content", responseCode = "204", content = [
                    Content(schema = Schema(implementation = UInt::class))
                ]
            ),
        ]
    )
    fun buscarListPessoa(): ResponseEntity<List<ProdutoResponse>> {
        return ResponseEntity.ok(produtoUseCase.buscarProdutos())
    }

    @PostMapping(
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun salvarPessoa(
        @RequestBody produtoRequest: ProdutoRequest
    ): ResponseEntity<ProdutoResponse?> {
        val produto: Produto = produtoMapper.produtoRequestToProduto(produtoRequest)
        return ResponseEntity<ProdutoResponse?>(produtoUseCase.salvarProduto(produto), HttpStatus.CREATED)
    }

    @PutMapping(
        value = ["/{id_produto}"],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun atualizarPessoa(
        @PathVariable(value = "id_produto") idProduto: Long,
        @RequestBody produtoRequest: ProdutoRequest
    ): ResponseEntity<ProdutoResponse?> {
        return ResponseEntity.ok(produtoUseCase.atualizarProduto(idProduto, produtoRequest))
    }
}