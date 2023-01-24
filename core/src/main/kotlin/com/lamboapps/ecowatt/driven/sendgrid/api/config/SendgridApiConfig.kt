package com.lamboapps.ecowatt.driven.sendgrid.api.config

import com.lamboapps.ecowatt.domain.spi.SendgridServiceSpi
import com.lamboapps.ecowatt.driven.sendgrid.api.SendgridServiceAdapter
import com.sendgrid.SendGrid
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.ImportRuntimeHints

@Configuration
@EnableConfigurationProperties(SendgridApiProperties::class)
@ImportRuntimeHints(SendgridApiRuntimeHints::class)
class SendgridApiConfig {

    @Bean
    fun sendGrid(properties: SendgridApiProperties): SendGrid {
        return SendGrid(properties.key)
    }

    @Bean
    fun sendgridServiceSpi(sendGridClient: SendGrid, properties: SendgridApiProperties): SendgridServiceSpi {
        return SendgridServiceAdapter(sendGridClient, properties)
    }
}