package fr.xebia.ecowatt.task.runner

import fr.xebia.ecowatt.domain.spi.EcowattServiceSpi
import fr.xebia.ecowatt.util.logger
import org.springframework.boot.CommandLineRunner

class LogEcowattSignalsRunner(private val ecowattServiceSpi: EcowattServiceSpi) : CommandLineRunner {

    private val log by logger()

    override fun run(vararg args: String?) {
        val accessToken = ecowattServiceSpi.getAccessToken()
        val signals = ecowattServiceSpi.getSignals(accessToken)
        log.info(signals.toString())
    }
}