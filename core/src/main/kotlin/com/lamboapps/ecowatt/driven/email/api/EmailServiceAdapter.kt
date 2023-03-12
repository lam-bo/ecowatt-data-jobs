package com.lamboapps.ecowatt.driven.email.api

import com.lamboapps.ecowatt.domain.spi.EmailServiceSpi
import com.lamboapps.ecowatt.driven.email.api.config.EmailApiProperties
import org.apache.commons.mail.DefaultAuthenticator
import org.apache.commons.mail.MultiPartEmail
import java.io.File
import javax.mail.internet.MimeBodyPart
import javax.mail.internet.MimeMultipart

class EmailServiceAdapter(
    private val properties: EmailApiProperties
) : EmailServiceSpi {

    override fun statusCodeFromGreenSignalTrigger(targetEmail: String): Int {

        val email = MultiPartEmail()

        email.hostName = properties.emailHost
        email.setSmtpPort(properties.emailPort)
        email.setAuthenticator(DefaultAuthenticator(properties.emailSender, properties.emailPassword))
        email.isStartTLSEnabled = true
        email.setFrom(properties.emailSender)
        email.addTo(targetEmail)
        email.subject = "EcoWatt"

        val multipart = MimeMultipart()
        val textPart = MimeBodyPart()
        val template = File(javaClass.classLoader.getResource("green.html").toURI()).readText()
        textPart.setContent(template, "text/html; charset=utf-8")

        val attachmentPart = MimeBodyPart()

        attachmentPart.attachFile(File(javaClass.classLoader.getResource("ecogestes.png").toURI()))
        attachmentPart.contentID = "content-cid"

        multipart.addBodyPart(textPart)
        multipart.addBodyPart(attachmentPart)

        email.setContent(multipart)
        email.send()
        return 200
    }
}