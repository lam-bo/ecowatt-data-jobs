package fr.xebia.ecowatt.driven.ecowatt.api.config

import fr.xebia.ecowatt.driven.ecowatt.api.model.AccessTokenApiResponse
import fr.xebia.ecowatt.driven.ecowatt.api.model.HourlyValue
import fr.xebia.ecowatt.driven.ecowatt.api.model.Signal
import fr.xebia.ecowatt.driven.ecowatt.api.model.SignalsApiResponse
import org.springframework.aot.hint.MemberCategory
import org.springframework.aot.hint.RuntimeHints
import org.springframework.aot.hint.RuntimeHintsRegistrar
import org.springframework.aot.hint.TypeReference


class EcowattApiRuntimeHints : RuntimeHintsRegistrar {

    override fun registerHints(hints: RuntimeHints, classLoader: ClassLoader?) {
        hints.reflection()
            .registerTypes(
                mutableListOf(
                    TypeReference.of(AccessTokenApiResponse::class.java),
                    TypeReference.of(SignalsApiResponse::class.java),
                    TypeReference.of(Signal::class.java),
                    TypeReference.of(HourlyValue::class.java),
                )
            ) {
                it.withMembers(
                    MemberCategory.INVOKE_DECLARED_CONSTRUCTORS,
                    MemberCategory.INVOKE_PUBLIC_METHODS
                )
            }
    }
}