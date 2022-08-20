package br.com.softwalter.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebServiceConfuguration {

    @Value("\${cors.origin_patterns:default}")
    private val corsOringemPatters: String = ""

    fun addCorsConfing(): WebMvcConfigurer {
        return object : WebMvcConfigurer {
            override fun addCorsMappings(registry: CorsRegistry) {
               // super.addCorsMappings(registry)
                val allowedOringins = corsOringemPatters.split(",").toTypedArray()
                registry.addMapping("/**")
                    .allowedMethods("*")
                    .allowedOrigins(*allowedOringins)
                    .allowCredentials(true)
            }
        }
    }
}