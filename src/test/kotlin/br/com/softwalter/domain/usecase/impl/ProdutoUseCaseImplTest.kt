package br.com.softwalter.domain.usecase.impl

import br.com.softwalter.domain.model.Produto
import br.com.softwalter.domain.repository.ProdutoRepository
import br.com.softwalter.presentation.mapper.ProdutoMapper
import br.com.softwalter.templates.ProdutoMockFactory
import br.com.softwalter.templates.ProdutoResponseMockFactory
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
internal class ProdutoUseCaseImplTest {

    @Mock
    private lateinit var produtoMapper: ProdutoMapper
    @Mock
    private lateinit var produtoRepository: ProdutoRepository
    @InjectMocks
    private lateinit var produtoUseCaseImpl: ProdutoUseCaseImpl

    @Test
    fun buscarProdutoPorId() {
    }

    @Test
    fun salvarProduto() {
    }

    @Test
    fun buscarProdutos() {
    }

    @Test
    fun atualizarProduto() {
    }

    @Test
    fun getProdutoMapper() {
        Mockito.`when`(produtoMapper.produtoToProdutoResponse(ProdutoMockFactory.mockProduto(1)))
            .thenReturn(ProdutoResponseMockFactory.mockProdutoResponse(1))
        val expectedProdutoResponse = ProdutoResponseMockFactory.mockProdutoResponse(1)
        val actualProdutoResponse = produtoMapper.produtoToProdutoResponse(ProdutoMockFactory.mockProduto(1))
        Assertions.assertEquals(expectedProdutoResponse, actualProdutoResponse)
    }

    @Test
    fun getProdutoRepository() {
    }
}