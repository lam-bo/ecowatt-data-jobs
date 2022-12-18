package fr.xebia.ecowatt.domain.spi

import fr.xebia.ecowatt.domain.model.EcowattSignal

interface EcowattServiceSpi {

    fun getAccessToken(): String
    fun getSignals(accessToken: String): List<EcowattSignal>
}