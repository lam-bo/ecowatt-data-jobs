package com.lamboapps.ecowatt.task.runner

import com.lamboapps.ecowatt.domain.model.SendgridEmail
import com.lamboapps.ecowatt.domain.spi.EcowattServiceSpi
import com.lamboapps.ecowatt.domain.spi.SendgridServiceSpi
import com.lamboapps.ecowatt.task.config.TaskProperties
import com.lamboapps.ecowatt.util.logger
import org.springframework.boot.CommandLineRunner


class SendEcowattMailRunner(
    private val ecowattServiceSpi: EcowattServiceSpi,
    private val sendgridServiceSpi: SendgridServiceSpi,
    private val taskProperties: TaskProperties
) : CommandLineRunner {

    private val log by logger()

    override fun run(vararg args: String?) {
        val accessToken = ecowattServiceSpi.getAccessToken()
        val signals = ecowattServiceSpi.getSignals(accessToken)
        log.info(signals.toString())
        val emailBody = signals.toString()
        val emailSubject = "test"
        val mailingList = taskProperties.mailingList.split(",")
        mailingList.forEach {
            val email = SendgridEmail(it, emailSubject, emailBody)
            val statusCodeResponse = sendgridServiceSpi.statusCodeFromEmailSent(email)
            log.info("Email sent to $it with status code $statusCodeResponse")
        }
    }
}