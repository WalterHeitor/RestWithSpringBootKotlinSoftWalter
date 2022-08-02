package br.com.softwalter.templates

import br.com.softwalter.domain.model.Produto

object ProdutoMockFactory {

    fun mockProduto ( number: Int) = Produto(
            codigoProduto = number.toLong(),
            nome = "nome $number",
            descricao = "descrição $number",
            precoCusto = (number + 5.0F),
            precoVenda = (number + 7.0F ),
            quantidade = 68,
            quantidadeMinima = 5
        )

    fun mockProdutoList (qtd: Int): ArrayList<Produto> {
        val produtos = ArrayList<Produto>()
         for (i in 0 .. qtd) {
             produtos.add(mockProduto(i))
         }
        return produtos
    }
}

