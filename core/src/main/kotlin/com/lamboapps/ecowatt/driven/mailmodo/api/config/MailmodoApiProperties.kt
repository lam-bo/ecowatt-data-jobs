package com.lamboapps.ecowatt.driven.mailmodo.api.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "mailmodo-api")
data class MailmodoApiProperties(
    val baseUrl: String,
    val key: String,
    val greenSignalCampaignId: String,
)

