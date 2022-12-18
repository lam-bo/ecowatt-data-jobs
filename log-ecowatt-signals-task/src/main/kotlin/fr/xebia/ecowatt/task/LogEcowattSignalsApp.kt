package fr.xebia.ecowatt.task

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import kotlin.system.exitProcess


@SpringBootApplication
class LogEcowattSignalsApp

fun main(args: Array<String>) {
    exitProcess(SpringApplication.exit(runApplication<LogEcowattSignalsApp>(*args)))
}