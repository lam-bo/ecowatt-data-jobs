package com.lamboapps.ecowatt.task.config

import com.lamboapps.ecowatt.domain.spi.EcowattServiceSpi
import com.lamboapps.ecowatt.domain.spi.EmailServiceSpi
import com.lamboapps.ecowatt.driven.ecowatt.api.config.EcowattApiConfig
import com.lamboapps.ecowatt.driven.email.api.config.EmailApiConfig
import com.lamboapps.ecowatt.task.runner.SendEcowattMailRunner
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.cloud.task.configuration.EnableTask
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@EnableTask
@EnableConfigurationProperties(TaskProperties::class)
@Import(value = [EcowattApiConfig::class, EmailApiConfig::class])
class TaskConfig {

    @Bean
    fun commandLineRunner(
        ecowattServiceSpi: EcowattServiceSpi,
        emailServiceSpi: EmailServiceSpi,
        taskProperties: TaskProperties
    ): CommandLineRunner {
        return SendEcowattMailRunner(ecowattServiceSpi, emailServiceSpi, taskProperties)
    }
}