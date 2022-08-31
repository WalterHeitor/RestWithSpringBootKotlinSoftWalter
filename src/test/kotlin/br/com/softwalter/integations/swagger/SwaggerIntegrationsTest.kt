package br.com.softwalter.integations.swagger

import br.com.softwalter.confisTest.ConfigsTest
import br.com.softwalter.integations.testcontainers.AbstractIntegrationTest
import io.restassured.RestAssured
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT )
class SwaggerIntegrationsTest : AbstractIntegrationTest() {

    @Test
    fun sholdDisplaySwaggerUiPage() {
        val content = RestAssured.given()
            .basePath("/swagger-ui/index.html")
            .port(ConfigsTest.SERVER_PORT)
                .`when`()
            .get()
            .then()
            .statusCode(200)
            .extract()
            .body()
                .asString()
        Assertions.assertTrue(content.contains("Swagger UI"))
    }
}