package com.lamboapps.ecowatt.task.runner

import com.lamboapps.ecowatt.domain.spi.EcowattServiceSpi
import com.lamboapps.ecowatt.domain.spi.EmailServiceSpi
import com.lamboapps.ecowatt.task.config.TaskProperties
import com.lamboapps.ecowatt.util.logger
import org.springframework.boot.CommandLineRunner


class SendEcowattMailRunner(
    private val ecowattServiceSpi: EcowattServiceSpi,
    private val emailServiceSpi: EmailServiceSpi,
    private val taskProperties: TaskProperties
) : CommandLineRunner {

    private val log by logger()

    override fun run(vararg args: String?) {
        val accessToken = ecowattServiceSpi.getAccessToken()
        val signals = ecowattServiceSpi.getSignals(accessToken)
        log.info(signals.toString())
        val responseStatus = emailServiceSpi.statusCodeFromGreenSignalTrigger(taskProperties.targetEmail)
        log.info("Email sent to ${taskProperties.targetEmail} with status code $responseStatus")
    }
}