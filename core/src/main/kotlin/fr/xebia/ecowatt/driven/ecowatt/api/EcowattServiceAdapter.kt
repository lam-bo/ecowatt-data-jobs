package fr.xebia.ecowatt.driven.ecowatt.api

import fr.xebia.ecowatt.domain.model.EcowattSignal
import fr.xebia.ecowatt.domain.spi.EcowattServiceSpi
import fr.xebia.ecowatt.driven.ecowatt.api.config.EcowattApiProperties
import fr.xebia.ecowatt.driven.ecowatt.api.model.AccessTokenApiResponse
import fr.xebia.ecowatt.driven.ecowatt.api.model.SignalsApiResponse
import fr.xebia.ecowatt.driven.ecowatt.api.model.SignalsMapper
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.WebClient

class EcowattServiceAdapter(
    private val webClient: WebClient,
    private val signalsMapper: SignalsMapper,
    private val ecowattApiProperties: EcowattApiProperties
) : EcowattServiceSpi {

    private val accessTokenPath = "/token/oauth"

    private val signalsPath = "/open_api/ecowatt/v4/"
        .plus(if (ecowattApiProperties.sandboxMode) "sandbox/" else "")
        .plus("signals")

    override fun getAccessToken(): String {
        return webClient.post().uri {
            it.path(accessTokenPath)
                .build()
        }.headers {
            it.setBasicAuth(ecowattApiProperties.encodedCredentials)
        }
            .retrieve()
            .bodyToMono(AccessTokenApiResponse::class.java)
            .map { it.accessToken }
            .block()!!
    }

    override fun getSignals(accessToken: String): List<EcowattSignal> {
        return webClient.get().uri {
            it.path(signalsPath)
                .build()
        }
            .headers {
                it.setBearerAuth(accessToken)
                it.accept = listOf(MediaType.APPLICATION_JSON)
            }
            .retrieve()
            .bodyToMono(SignalsApiResponse::class.java)
            .map(signalsMapper.drivenToDomain)
            .block()!!
    }
}