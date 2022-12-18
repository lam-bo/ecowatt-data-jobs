package fr.xebia.ecowatt.driven.ecowatt.api.config

import fr.xebia.ecowatt.domain.spi.EcowattServiceSpi
import fr.xebia.ecowatt.driven.ecowatt.api.EcowattServiceAdapter
import fr.xebia.ecowatt.driven.ecowatt.api.model.SignalsMapper
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.ImportRuntimeHints
import org.springframework.web.reactive.function.client.WebClient


@Configuration
@EnableConfigurationProperties(EcowattApiProperties::class)
@ImportRuntimeHints(EcowattApiRuntimeHints::class)
class EcowattApiConfig {

    @Bean
    fun ecowattWebClient(properties: EcowattApiProperties): WebClient {
        return WebClient.create(properties.baseUrl)
    }

    @Bean
    fun signalsMapper(): SignalsMapper {
        return SignalsMapper()
    }

    @Bean
    fun ecowattServiceSpi(
        webClient: WebClient,
        signalsMapper: SignalsMapper,
        properties: EcowattApiProperties
    ): EcowattServiceSpi {
        return EcowattServiceAdapter(webClient, signalsMapper, properties)
    }
}