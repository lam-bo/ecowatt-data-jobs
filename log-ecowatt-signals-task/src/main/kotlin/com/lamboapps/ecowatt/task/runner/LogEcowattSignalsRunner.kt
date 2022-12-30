package com.lamboapps.ecowatt.task.runner

import com.lamboapps.ecowatt.domain.spi.EcowattServiceSpi
import com.lamboapps.ecowatt.util.logger
import org.springframework.boot.CommandLineRunner

class LogEcowattSignalsRunner(private val ecowattServiceSpi: EcowattServiceSpi) : CommandLineRunner {

    private val log by logger()

    override fun run(vararg args: String?) {
        val accessToken = ecowattServiceSpi.getAccessToken()
        val signals = ecowattServiceSpi.getSignals(accessToken)
        log.info(signals.toString())
    }
}