package com.lamboapps.ecowatt.domain.spi

import com.lamboapps.ecowatt.domain.model.SendgridEmail

interface SendgridServiceSpi {

    fun statusCodeFromEmailSent(emailData: SendgridEmail): Int
}