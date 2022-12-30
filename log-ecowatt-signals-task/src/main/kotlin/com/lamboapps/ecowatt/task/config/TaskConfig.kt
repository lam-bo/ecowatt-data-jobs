package com.lamboapps.ecowatt.task.config

import com.lamboapps.ecowatt.domain.spi.EcowattServiceSpi
import com.lamboapps.ecowatt.driven.ecowatt.api.config.EcowattApiConfig
import com.lamboapps.ecowatt.task.runner.LogEcowattSignalsRunner
import org.springframework.boot.CommandLineRunner
import org.springframework.cloud.task.configuration.EnableTask
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import


@Configuration
@EnableTask
@Import(value = [EcowattApiConfig::class])
class TaskConfig {

    @Bean
    fun commandLineRunner(ecowattServiceSpi: EcowattServiceSpi): CommandLineRunner {
        return LogEcowattSignalsRunner(ecowattServiceSpi)
    }
}