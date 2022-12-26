package fr.xebia.convention

plugins {
    id("fr.xebia.convention.kotlin-spring")
    `java-library`
}

tasks.stream()
    .filter { it.name.startsWith("boot")}
    .forEach{ it.enabled = false }