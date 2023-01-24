package com.lamboapps.ecowatt.task.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "send-ecowatt-email-task")
data class TaskProperties(
    val mailContentsProfile: String,
    val mailingList: String,
)