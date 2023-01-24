package com.lamboapps.ecowatt.domain.model

data class SendgridEmail(
    val toEmail: String,
    val subject: String,
    val body: String
)