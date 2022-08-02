package br.com.softwalter.domain.usecase.impl

import br.com.softwalter.domain.model.Pessoa
import br.com.softwalter.domain.repository.PessoaRepository
import br.com.softwalter.exceptions.PessoaNullException
import br.com.softwalter.presentation.mapper.PessoaMapper
import br.com.softwalter.presentation.pessoa.dto.v1.PessoaResponse
import br.com.softwalter.templates.PessoaMockFactory
import br.com.softwalter.templates.PessoaResponseMockFactory
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
internal class PessoaUseCaseImplTest {

    @Mock
    private lateinit var pessoaRepository: PessoaRepository

    @Mock
    private lateinit var pessoaMapper: PessoaMapper

    @InjectMocks
    private lateinit var pessoaUseCaseImpl: PessoaUseCaseImpl

    @BeforeEach
    fun setUp() {
//        inputObject = PessoaMockFactory.criarPessoa()
//        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun buscarPessoaPorId() {

        val expectedRepository = PessoaMockFactory.criarPessoa()
        Mockito.`when`(pessoaRepository.findById(1L))
            .thenReturn(Optional.of(expectedRepository))
        val expectedPessoaResponse = PessoaResponseMockFactory.criarPessoaResponse()
        Mockito.`when`(pessoaMapper.pessoaToPessoaResponse(expectedRepository))
            .thenReturn(expectedPessoaResponse)
        val actualRepository = pessoaRepository.findById(1L)
        val actualpessoaResponse = pessoaMapper.pessoaToPessoaResponse(expectedRepository)
        val actual: PessoaResponse? = pessoaUseCaseImpl.buscarPessoaPorId(1L)

        assertBuscaEInserssao(
            actualRepository.get(),
            actualpessoaResponse,
            actual,
            expectedRepository,
            expectedPessoaResponse
        )
    }

    @Test
    fun buscarPessoas() {

        val expectedListRepository: MutableList<Pessoa> = PessoaMockFactory.criarPessoas()
        Mockito.`when`(pessoaRepository.findAll())
            .thenReturn(expectedListRepository)
        val expectedPessoaResponse: MutableList<PessoaResponse> = PessoaResponseMockFactory.criarPessoasResponses()
        Mockito.`when`(pessoaMapper.pessoasToListResponse(expectedListRepository))
            .thenReturn(expectedPessoaResponse)
        val actualRepository = pessoaRepository.findAll()
        val actualpessoaResponse = pessoaMapper.pessoasToListResponse(expectedListRepository)
        val actualList = pessoaUseCaseImpl.buscarPessoas()

        Assertions.assertNotNull(actualRepository)
        Assertions.assertNotNull(actualpessoaResponse)
        Assertions.assertNotNull(actualList)
        Assertions.assertEquals(3,actualList.size)
        Assertions.assertNotNull(actualList[0].links)
        Assertions.assertNotNull(actualList.get(0).links.toString().contains("</cadastro/v1/pessoas/1>,rel=\"self\""))
        Assertions.assertEquals(expectedListRepository, actualRepository)
        Assertions.assertEquals(expectedPessoaResponse, actualpessoaResponse)
        Assertions.assertEquals(expectedPessoaResponse, actualList)
    }

    @Test
    fun salvarPessoa() {

        val expectedRepository = PessoaMockFactory.criarPessoa()
        val persistido = expectedRepository.copy()
        Mockito.`when`(pessoaRepository.save(expectedRepository))
            .thenReturn(persistido)
        val expectedPessoaResponse = PessoaResponseMockFactory.criarPessoaResponse()
        Mockito.`when`(pessoaMapper.pessoaToPessoaResponse(persistido))
            .thenReturn(expectedPessoaResponse)
        val actualRepository = pessoaRepository.save(PessoaMockFactory.criarPessoa())
        val actualpessoaResponse = pessoaMapper.pessoaToPessoaResponse(expectedRepository)
        val actual: PessoaResponse? = pessoaUseCaseImpl.salvarPessoa(expectedRepository)

        assertBuscaEInserssao(
            actualRepository,
            actualpessoaResponse,
            actual,
            expectedRepository,
            expectedPessoaResponse
        )
    }

    @Test
    fun salvarPessoaNullException() {

        val exception: Exception = Assertions.assertThrows(
            PessoaNullException::class.java
        ) {
            pessoaUseCaseImpl.salvarPessoa(null)
        }
        val expectedMensage = "Não e permitido percistir o objeto no banco"
        val actualMensage = exception.message

        Assertions.assertTrue(actualMensage.equals(expectedMensage))
    }

    @Test
    fun buscarPessoa() {
    }

    @Test
    fun atualizarPessoa() {
        val expectedRepository = PessoaMockFactory.criarPessoa()
        val persistido = expectedRepository.copy()
        Mockito.`when`(pessoaRepository.findById(1L))
            .thenReturn(Optional.of(expectedRepository))
        Mockito.`when`(pessoaRepository.save(expectedRepository))
            .thenReturn(persistido)
        val expectedPessoaResponse = PessoaResponseMockFactory.criarPessoaResponse()
        Mockito.`when`(pessoaMapper.pessoaToPessoaResponse(expectedRepository))
            .thenReturn(expectedPessoaResponse)
        val actualRepository = pessoaRepository.findById(1L)
        val actualpessoaResponse = pessoaMapper.pessoaToPessoaResponse(expectedRepository)
        val actual: PessoaResponse? = pessoaUseCaseImpl.atualizarPessoa(1L)

        assertBuscaEInserssao(
            actualRepository.get(),
            actualpessoaResponse,
            actual,
            expectedRepository,
            expectedPessoaResponse
        )
    }

    @Test
    fun getPessoaMapper() {
    }

    private fun assertBuscaEInserssao(
        actualRepository: Pessoa,
        actualpessoaResponse: PessoaResponse,
        actual: PessoaResponse?,
        expectedRepository: Pessoa,
        expectedPessoaResponse: PessoaResponse?
    ) {
        Assertions.assertNotNull(actualRepository)
        Assertions.assertNotNull(actualpessoaResponse)
        Assertions.assertNotNull(actual)
        Assertions.assertNotNull(actual!!.idPessoa)
        Assertions.assertNotNull(actual.links)
        Assertions.assertNotNull(actual.links.toString().contains("</cadastro/v1/pessoas/1>,rel=\"self\""))
        Assertions.assertEquals(expectedRepository, actualRepository)
        Assertions.assertEquals(expectedPessoaResponse, actualpessoaResponse)
        Assertions.assertEquals(expectedPessoaResponse, actual)
    }
}