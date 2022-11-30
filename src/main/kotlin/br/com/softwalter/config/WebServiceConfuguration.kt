package br.com.softwalter.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class  WebServiceConfuguration : WebMvcConfigurer{

    @Value("\${cors.origin_patterns:default}")
    private val corsOringemPatters: String = ""

//    fun addCorsConfing(): WebMvcConfigurer {
//        return object : WebMvcConfigurer {
//            override fun addCorsMappings(registry: CorsRegistry) {
//               // super.addCorsMappings(registry)
//                val allowedOringins = corsOringemPatters.split(",").toTypedArray()
//                registry.addMapping("/**")
//                    .allowedMethods("*")
//                    .allowedOrigins(*allowedOringins)
//                    .allowCredentials(true)
//            }
//        }
//    }
    override fun addCorsMappings(registry: CorsRegistry) {
        val allowedOrigins = corsOringemPatters.split(",").toTypedArray()
        registry.addMapping("/**")
            .allowedMethods("*")
            .allowedOrigins(*allowedOrigins)
            .allowCredentials(true)
    }
}