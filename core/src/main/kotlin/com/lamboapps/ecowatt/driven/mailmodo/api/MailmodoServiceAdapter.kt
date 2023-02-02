package com.lamboapps.ecowatt.driven.mailmodo.api

import com.lamboapps.ecowatt.domain.spi.MailmodoServiceSpi
import com.lamboapps.ecowatt.driven.mailmodo.api.config.MailmodoApiProperties
import com.lamboapps.ecowatt.driven.mailmodo.api.model.MailmodoTriggerRequest
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient

class MailmodoServiceAdapter(
    private val webClient: WebClient,
    private val properties: MailmodoApiProperties
) : MailmodoServiceSpi {

    private val campaignTriggerPath = "/api/v1/triggerCampaign/"

    override fun statusCodeFromGreenSignalTrigger(email: String): Int {
        return webClient.post().uri {
            it.path(campaignTriggerPath.plus(properties.greenSignalCampaignId))
                .build()
        }
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(MailmodoTriggerRequest(email)))
            .retrieve()
            .toBodilessEntity()
            .block()!!
            .statusCode
            .value()
    }
}