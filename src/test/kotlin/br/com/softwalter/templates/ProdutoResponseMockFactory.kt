package br.com.softwalter.templates

import br.com.softwalter.domain.model.Produto
import br.com.softwalter.presentation.produto.dto.ProdutoResponse

object ProdutoResponseMockFactory {

    fun mockProdutoResponse (number: Int ) = ProdutoResponse(
        codigoProduto = number.toLong(),
        nome = "nome $number",
        descricao = "descrição $number",
        precoCusto = (number + 5.0F),
        precoVenda = (number + 7.0F ),
        quantidade = 68,
        quantidadeMinima = 5
    )

    fun mockProdutoResponseList ( qtd: Int) : ArrayList<ProdutoResponse> {
        val produtosResponse = ArrayList<ProdutoResponse>()
        for (i in 0 .. qtd) {
            produtosResponse.add(mockProdutoResponse(i))
        }
        return produtosResponse
    }
}