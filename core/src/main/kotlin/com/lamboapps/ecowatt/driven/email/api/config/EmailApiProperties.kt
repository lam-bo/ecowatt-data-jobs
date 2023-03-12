package com.lamboapps.ecowatt.driven.email.api.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "email-api")
data class EmailApiProperties(
    val emailSender: String,
    val emailPassword: String,
    val emailHost: String,
    val emailPort: Int,
)