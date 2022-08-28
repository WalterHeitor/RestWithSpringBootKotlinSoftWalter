package br.com.softwalter.integations.controller.cors.withjson

import br.com.softwalter.confisTest.ConfigsTest
import br.com.softwalter.integations.dto.PessoaResponse
import br.com.softwalter.integations.testcontainers.AbstractIntegrationTest
import br.com.softwalter.presentation.users.AccountCredentialsRequest
import br.com.softwalter.presentation.users.TokenResponse
import io.restassured.RestAssured
import io.restassured.builder.RequestSpecBuilder
import io.restassured.filter.log.LogDetail
import io.restassured.filter.log.RequestLoggingFilter
import io.restassured.filter.log.ResponseLoggingFilter
import io.restassured.specification.RequestSpecification
import org.junit.jupiter.api.*
import org.springframework.boot.test.context.SpringBootTest
import org.testcontainers.shaded.com.fasterxml.jackson.databind.DeserializationFeature
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PersonControllerCorsWithJson : AbstractIntegrationTest() {
    private lateinit var specification: RequestSpecification
    private lateinit var objectMapper: ObjectMapper
    private lateinit var pessoaResponse: PessoaResponse

    private lateinit var token: String

    @BeforeAll
    fun setupTests() {

        objectMapper = ObjectMapper()
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        pessoaResponse = PessoaResponse()
        token = ""
    }

    @Test
    @Order(0)
    fun autorizacao() {
        val accountCredentialsRequest: AccountCredentialsRequest = AccountCredentialsRequest(
            userName = "leandro",
            password = "adimn123"
        )

        token = RestAssured.given()
            .basePath("/auth/signin")
                .port(ConfigsTest.SERVER_PORT)
                .contentType(ConfigsTest.CONTENT_TYPE_JSON)
                .body(accountCredentialsRequest)
            .`when`()
                .post()
                    .then()
            .statusCode(200)
                .extract()
                .body()
            .`as`(TokenResponse::class.java)
            .accessToken!!
    }

    @Test
    @Order(1)
    fun testeDeCriacao() {

        mockPessoaResponse()

        specification = RequestSpecBuilder()
            .addHeader(
                ConfigsTest.HEADER_PARAM_ORIGIN,
                ConfigsTest.ORIGIN_SOFTWALTER
            )
//            .addHeader(ConfigsTest.READER_PARAM_AUTHORIZATION,
//            "Bearer $token")
            .setBasePath("/cadastro/v1/pessoas")
            .setPort(ConfigsTest.SERVER_PORT)
            .addFilter(RequestLoggingFilter(LogDetail.ALL))
            .addFilter(ResponseLoggingFilter(LogDetail.ALL))
            .build()

        val content = RestAssured.given()
            .spec(specification)
            .contentType(ConfigsTest.CONTENT_TYPE_JSON)
            .body(pessoaResponse)
            .`when`()
            .post()
            .then()
            .statusCode(201)
            .extract()
            .body()
            .asString()

        val criacaoPessoaResponse = objectMapper.readValue(
            content,
            PessoaResponse::class.java
        )
        Assertions.assertNotNull(criacaoPessoaResponse.idPessoa)
    }

    @Test
    @Order(2)
    fun testeDeCriacaoComErroDeOringin() {

        mockPessoaResponse()

        specification = RequestSpecBuilder()
            .addHeader(
                ConfigsTest.HEADER_PARAM_ORIGIN,
                ConfigsTest.ORIGIN_WALTERSOFT
            )
//            .addHeader(ConfigsTest.READER_PARAM_AUTHORIZATION,
//                "Bearer $token")
            .setBasePath("/cadastro/v1/pessoas")
            .setPort(ConfigsTest.SERVER_PORT)
            .addFilter(RequestLoggingFilter(LogDetail.ALL))
            .addFilter(ResponseLoggingFilter(LogDetail.ALL))
            .build()

        val content = RestAssured.given()
            .spec(specification)
            .contentType(ConfigsTest.CONTENT_TYPE_JSON)
            .body(pessoaResponse)
            .`when`()
            .post()
            .then()
            .statusCode(403)
            .extract()
            .body()
            .asString()

        Assertions.assertEquals("Invalid CORS request", content)
    }

    @Test
    @Order(3)
    fun testeFindById() {

        mockPessoaResponse()

        specification = RequestSpecBuilder()
            .addHeader(
                ConfigsTest.HEADER_PARAM_ORIGIN,
                ConfigsTest.ORIGIN_LOCALHOST
            )
//            .addHeader(ConfigsTest.READER_PARAM_AUTHORIZATION,
//                "Bearer $token")
            .setBasePath("/cadastro/v1/pessoas")
            .setPort(ConfigsTest.SERVER_PORT)
            .addFilter(RequestLoggingFilter(LogDetail.ALL))
            .addFilter(ResponseLoggingFilter(LogDetail.ALL))
            .build()

        val content = RestAssured.given()
            .spec(specification)
            .contentType(ConfigsTest.CONTENT_TYPE_JSON)
            .pathParam("id_pessoa", pessoaResponse.idPessoa)
            .`when`()["{id_pessoa}"]
            .then()
            .statusCode(200)
            .extract()
            .body()
            .asString()

        val criacaoPessoaResponse = objectMapper.readValue(
            content,
            PessoaResponse::class.java
        )
        Assertions.assertNotNull(criacaoPessoaResponse.idPessoa)
        Assertions.assertNotNull(criacaoPessoaResponse.nome)
        Assertions.assertNotNull(criacaoPessoaResponse.email)
    }

    @Test
    @Order(4)
    fun testeFindByIdComErroDeOringin() {

        mockPessoaResponse()

        specification = RequestSpecBuilder()
            .addHeader(
                ConfigsTest.HEADER_PARAM_ORIGIN,
                ConfigsTest.ORIGIN_WALTERSOFT
            )
//            .addHeader(ConfigsTest.READER_PARAM_AUTHORIZATION,
//                "Bearer $token")
            .setBasePath("/cadastro/v1/pessoas")
            .setPort(ConfigsTest.SERVER_PORT)
            .addFilter(RequestLoggingFilter(LogDetail.ALL))
            .addFilter(ResponseLoggingFilter(LogDetail.ALL))
            .build()

        val content = RestAssured.given()
            .spec(specification)
            .contentType(ConfigsTest.CONTENT_TYPE_JSON)
            .pathParam("id_pessoa", pessoaResponse.idPessoa)
            .`when`()["{id_pessoa}"]
            .then()
            .statusCode(403)
            .extract()
            .body()
            .asString()

        Assertions.assertEquals("Invalid CORS request", content)
    }

    fun mockPessoaResponse() {
        pessoaResponse.idPessoa = 1L
        pessoaResponse.nome = "walter"
        pessoaResponse.email = "walter@email.com"
    }
}