package com.lamboapps.ecowatt.driven.sendgrid.api.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "sendgrid-api")
data class SendgridApiProperties(
    val key: String,
    val fromEmail: String
)

