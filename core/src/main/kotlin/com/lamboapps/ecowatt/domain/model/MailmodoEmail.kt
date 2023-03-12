package com.lamboapps.ecowatt.domain.model

data class MailmodoEmail(
    val toEmail: String,
    val subject: String,
    val body: String
)