package com.lamboapps.ecowatt.domain.spi

import com.lamboapps.ecowatt.domain.model.EcowattSignal

interface EcowattServiceSpi {

    fun getAccessToken(): String
    fun getSignals(accessToken: String): List<EcowattSignal>
}