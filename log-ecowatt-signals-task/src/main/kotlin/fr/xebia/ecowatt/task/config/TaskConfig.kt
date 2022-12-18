package fr.xebia.ecowatt.task.config

import fr.xebia.ecowatt.domain.spi.EcowattServiceSpi
import fr.xebia.ecowatt.driven.ecowatt.api.config.EcowattApiConfig
import fr.xebia.ecowatt.task.runner.LogEcowattSignalsRunner
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