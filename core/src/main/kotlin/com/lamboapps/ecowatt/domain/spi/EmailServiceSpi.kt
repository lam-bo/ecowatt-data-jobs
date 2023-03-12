package com.lamboapps.ecowatt.domain.spi

interface EmailServiceSpi {

    fun statusCodeFromGreenSignalTrigger(targetEmail: String): Int

}