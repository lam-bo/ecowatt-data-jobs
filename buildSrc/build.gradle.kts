
plugins {
    `kotlin-dsl`
}

val kotlinVersion = "1.7.21"
val springBootVersion = "3.0.0"
val qomoonGitVersion = "6.3.7"
val graalvmToolsVersion = "0.9.19"

dependencies {
    // Add necessary dependencies for plugins
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${kotlinVersion}")
    implementation("org.jetbrains.kotlin:kotlin-serialization:${kotlinVersion}")
    implementation("org.jetbrains.kotlin:kotlin-allopen:${kotlinVersion}")
    implementation("org.jetbrains.kotlin:kotlin-noarg:${kotlinVersion}")
    implementation("org.jetbrains.kotlin.plugin.spring:org.jetbrains.kotlin.plugin.spring.gradle.plugin:${kotlinVersion}")
    implementation("org.springframework.boot:spring-boot-gradle-plugin:${springBootVersion}")
    implementation("org.graalvm.buildtools.native:org.graalvm.buildtools.native.gradle.plugin:${graalvmToolsVersion}")
    implementation("me.qoomon:gradle-git-versioning-plugin:${qomoonGitVersion}")
}
