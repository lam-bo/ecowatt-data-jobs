package fr.xebia.ecowatt.driven.ecowatt.api.model

import fr.xebia.ecowatt.domain.model.EcowattSignal
import fr.xebia.ecowatt.domain.model.ElectricalGridStatus
import java.time.LocalDate
import java.time.ZoneId

class SignalsMapper {

    val drivenToDomain: (SignalsApiResponse) -> List<EcowattSignal> = { response ->
        response.signals.map { signal ->
            val hourlyStatuses = signal.values.associate { it.step to gridStatus(it.value) }
            EcowattSignal(
                signal.fileLastGeneration.toInstant(),
                LocalDate.ofInstant(signal.day.toInstant(), ZoneId.of(("Europe/Paris"))),
                gridStatus(signal.dailyValue),
                signal.message,
                hourlyStatuses
            )
        }
    }

    private fun gridStatus(value: Int): ElectricalGridStatus {
        return when (value) {
            1 -> ElectricalGridStatus.GREEN
            2 -> ElectricalGridStatus.ORANGE
            3 -> ElectricalGridStatus.RED
            else -> ElectricalGridStatus.GREEN
        }
    }

}