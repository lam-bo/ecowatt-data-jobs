package com.lamboapps.ecowatt.driven.sendgrid.api

import com.lamboapps.ecowatt.domain.model.SendgridEmail
import com.lamboapps.ecowatt.domain.spi.SendgridServiceSpi
import com.lamboapps.ecowatt.driven.sendgrid.api.config.SendgridApiProperties
import com.sendgrid.*

class SendgridServiceAdapter(
    private val sendGridClient: SendGrid,
    private val properties: SendgridApiProperties
) : SendgridServiceSpi {

    override fun statusCodeFromEmailSent(emailData: SendgridEmail): Int {
        val from = Email(properties.fromEmail)
        val subject = emailData.subject
        val to = Email(emailData.toEmail)
        val content = Content("text/plain", emailData.body)
        val sendgridEmail = Mail(from, subject, to, content)
        val sendgridRequest = Request()
        sendgridRequest.method = Method.POST
        sendgridRequest.endpoint = "mail/send";
        sendgridRequest.body = sendgridEmail.build();
        val sendgridResponse = sendGridClient.api(sendgridRequest);
        return sendgridResponse.statusCode
    }
}