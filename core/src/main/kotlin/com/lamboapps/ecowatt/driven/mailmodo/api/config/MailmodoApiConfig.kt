package com.lamboapps.ecowatt.driven.mailmodo.api.config

import com.lamboapps.ecowatt.domain.spi.MailmodoServiceSpi
import com.lamboapps.ecowatt.driven.mailmodo.api.MailmodoServiceAdapter
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.ImportRuntimeHints
import org.springframework.web.reactive.function.client.WebClient

@Configuration
@EnableConfigurationProperties(MailmodoApiProperties::class)
@ImportRuntimeHints(MailmodoApiRuntimeHints::class)
class MailmodoApiConfig {

    @Bean
    @Qualifier("mailmodo")
    fun mailmodoWebClient(properties: MailmodoApiProperties): WebClient {
        return WebClient.builder()
            .baseUrl(properties.baseUrl)
            .defaultHeader("mmApiKey", properties.key)
            .build()
    }

    @Bean
    fun mailmodoServiceSpi(
        @Qualifier("mailmodo") webClient: WebClient,
        properties: MailmodoApiProperties
    ): MailmodoServiceSpi {
        return MailmodoServiceAdapter(webClient, properties)
    }
}