package com.lamboapps.ecowatt.driven.ecowatt.api.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*


data class SignalsApiResponse(
    @JsonProperty("signals")
    val signals: List<Signal>
)

data class Signal(
    @JsonProperty("GenerationFichier")
    val fileLastGeneration: Date,
    @JsonProperty("jour")
    val day: Date,
    @JsonProperty("dvalue")
    val dailyValue: Int,
    @JsonProperty("message")
    val message: String,
    @JsonProperty("values")
    val values: List<HourlyValue>
)

data class HourlyValue(
    @JsonProperty("pas")
    val step: Int,
    @JsonProperty("hvalue")
    val value: Int
)
