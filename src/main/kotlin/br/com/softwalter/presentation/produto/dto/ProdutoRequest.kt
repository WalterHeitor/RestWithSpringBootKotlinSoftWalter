package br.com.softwalter.presentation.produto.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class ProdutoRequest(
    @field:JsonProperty("nome")
    val nome: String,
    @field:JsonProperty("descricao")
    val descricao: String,
    @field:JsonProperty("preco_custo")
    val precoCusto: Float,
    @field:JsonProperty("preco_venda")
    val precoVenda: Float,
    @field:JsonProperty("quantidade")
    val quantidade: Int,
    @field:JsonProperty("quantidade_minima")
    val quantidadeMinima: Int
) {
}