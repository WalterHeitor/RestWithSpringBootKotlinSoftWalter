package br.com.softwalter.domain.model

import javax.persistence.*

@Entity()
@Table(name = "produto")
data class Produto(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_produto")
    val codigoProduto: Long? = null,
    @Column(name = "nome")
    var nome: String = "",
    @Column(name = "descricao")
    var descricao: String = "",
    @Column(name = "preco_custo")
    var precoCusto: Float = 0.0F,
    @Column(name = "preco_venda")
    var precoVenda: Float = 0.0F,
    @Column(name = "quantidade")
    var quantidade: Int = 0,
    @Column(name = "quantidade_minima")
    var quantidadeMinima: Int = 0
) {
}