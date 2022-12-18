package fr.xebia.ecowatt.driven.ecowatt.api.model

import com.fasterxml.jackson.annotation.JsonProperty

data class AccessTokenApiResponse(
    @JsonProperty("access_token")
    val accessToken: String,
    @JsonProperty("token_type")
    val tokenType: String,
    @JsonProperty("expires_in")
    val expiresIn: Int
)