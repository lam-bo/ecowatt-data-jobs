package com.lamboapps.ecowatt.driven.ecowatt.api.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "ecowatt-api")
data class EcowattApiProperties(
    val baseUrl: String,
    val encodedCredentials: String,
    val sandboxMode: Boolean
)