package com.lamboapps.convention

plugins {
    id("com.lamboapps.convention.kotlin-spring")
    `java-library`
}

tasks.stream()
    .filter { it.name.startsWith("boot")}
    .forEach{ it.enabled = false }