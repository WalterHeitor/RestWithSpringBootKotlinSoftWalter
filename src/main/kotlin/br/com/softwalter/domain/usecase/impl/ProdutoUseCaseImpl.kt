package br.com.softwalter.domain.usecase.impl

import br.com.softwalter.domain.model.Produto
import br.com.softwalter.domain.repository.ProdutoRepository
import br.com.softwalter.domain.usecase.ProdutoUseCase
import br.com.softwalter.exceptions.PessoaNullException
import br.com.softwalter.presentation.mapper.ProdutoMapper
import br.com.softwalter.presentation.produto.ProdutoController
import br.com.softwalter.presentation.produto.dto.ProdutoRequest
import br.com.softwalter.presentation.produto.dto.ProdutoResponse
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class ProdutoUseCaseImpl(
    val produtoMapper: ProdutoMapper,
    val produtoRepository: ProdutoRepository
) : ProdutoUseCase {
    private val logger = Logger.getLogger(ProdutoUseCaseImpl::class.java.name)

    override fun buscarProdutoPorId(idProduto: Long): ProdutoResponse? {

        val produto: Produto? = buscandoProduto(idProduto)
        logger.info("usecase - produto encontrada no Banco de Dados ...")
        val produtoResponse: ProdutoResponse =
            produtoMapper.produtoToProdutoResponse(produto!!)
        adicionadoHateoas(produtoResponse)

        return produtoResponse
    }

    override fun salvarProduto(produto: Produto?): ProdutoResponse? {
        if (produto == null) throw PessoaNullException()
        logger.info("usecase - Salvando produto no Banco de Dados ...")
        val produtoResp: Produto = produtoRepository.save(produto)
        logger.info("usecase - produto salva com sucesso no Banco de Dados ...")
        val produtoResponse: ProdutoResponse =
            produtoMapper.produtoToProdutoResponse(produtoResp)
        adicionadoHateoas(produtoResponse)
        return produtoResponse
    }

    override fun buscarProdutos(): List<ProdutoResponse> {
        logger.info("usecase - buscando produtos no Banco de Dados ...")
        val pessoas: MutableList<Produto> = produtoRepository.findAll()
        logger.info("usecase - busca de produtos no Banco de Dados ...")
        val pessoasResponse: List<ProdutoResponse> = produtoMapper.pessoasToListResponse(pessoas)
        for (response in pessoasResponse) {
            adicionadoHateoas(response)
        }
        return pessoasResponse
    }

    override fun atualizarProduto(idProduto: Long, produtoRequest: ProdutoRequest): ProdutoResponse? {
        val produto: Produto? = buscandoProduto(idProduto)
        produto!!.nome = produtoRequest.nome
        produto.descricao = produtoRequest.descricao
        produto.precoCusto = produtoRequest.precoCusto
        produto.precoVenda = produtoRequest.precoVenda
        produto.quantidade = produtoRequest.quantidade
        produto.quantidadeMinima = produtoRequest.quantidadeMinima
        val pessoaPersist = produtoRepository.save(produto)
        logger.info("usecase - produto atualizada com sucesso no Banco de Dados ...")
        return pessoaPersist.let { produtoMapper.produtoToProdutoResponse(it) }
    }

    private fun buscandoProduto(idProduto: Long): Produto? {
        logger.info("usecase - buscando produto no Banco de Dados ...")
        val produto: Produto? = produtoRepository.findById(idProduto)
            .orElseThrow { RuntimeException("lancar exectipito banco") }
        return produto
    }

    private fun adicionadoHateoas(produtoResponse: ProdutoResponse) {
        val withSeltRel = linkTo(ProdutoController::class.java)
            .slash(produtoResponse.codigoProduto).withSelfRel()
        produtoResponse.add(withSeltRel)
    }
}