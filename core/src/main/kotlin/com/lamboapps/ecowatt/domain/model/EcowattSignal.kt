package com.lamboapps.ecowatt.domain.model

import java.time.Instant
import java.time.LocalDate

data class EcowattSignal(
    val lastUpdate: Instant,
    val day: LocalDate,
    val dailyStatus: ElectricalGridStatus,
    val message: String,
    val hourlyStatuses: Map<Int, ElectricalGridStatus>,
)

enum class ElectricalGridStatus {
    GREEN, ORANGE, RED
}