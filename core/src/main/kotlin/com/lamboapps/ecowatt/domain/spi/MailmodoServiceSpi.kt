package com.lamboapps.ecowatt.domain.spi

interface MailmodoServiceSpi {

    fun statusCodeFromGreenSignalTrigger(email: String): Int
}