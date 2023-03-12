package com.lamboapps.ecowatt.driven.email.api.config

import com.lamboapps.ecowatt.domain.spi.EmailServiceSpi
import com.lamboapps.ecowatt.driven.email.api.EmailServiceAdapter
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.ImportRuntimeHints

@Configuration
@EnableConfigurationProperties(EmailApiProperties::class)
@ImportRuntimeHints(EmailApiRuntimeHints::class)
class EmailApiConfig {

    @Bean
    fun emailServiceSpi(
        properties: EmailApiProperties,
    ): EmailServiceSpi {
        return EmailServiceAdapter(properties)
    }
}