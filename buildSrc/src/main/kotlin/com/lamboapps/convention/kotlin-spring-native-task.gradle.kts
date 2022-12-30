package com.lamboapps.convention

import org.springframework.boot.gradle.tasks.bundling.BootBuildImage
import org.springframework.boot.gradle.tasks.run.BootRun

plugins {
    id("com.lamboapps.convention.kotlin-spring")
    id("me.qoomon.git-versioning")
    id("org.graalvm.buildtools.native")
}

val springCloudVersion = "2022.0.0-RC2"

dependencies {
    implementation(platform("org.springframework.cloud:spring-cloud-dependencies:${springCloudVersion}"))

    implementation(project(":core"))
    implementation("org.springframework.cloud:spring-cloud-starter-task")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    runtimeOnly("com.h2database:h2")
    runtimeOnly("org.postgresql:postgresql")
}



/** Values provided during Github actions build workflow **/
val isRegistryPublish = (project.properties.getOrDefault("registry.isPublish", "false") as String)
    .let { it.toBoolean() }
val registryBaseUrl = project.properties.getOrDefault("registry.baseUrl", "docker.io") as String
val registryImagePath = project.properties.getOrDefault("registry.imagePath", "ecowatt-data/tasks") as String
val registryUsername = project.properties.get("registry.username") as String?
val registryPassword = project.properties.get("registry.password") as String?
val fullImageName = listOf(registryBaseUrl, registryImagePath, project.name)
    .filterNotNull()
    .joinToString("/")
lateinit var additionalTags : List<String>
gitVersioning.apply {
    refs {
        branch("main") {
            additionalTags = listOf(fullImageName.plus(":main"))
        }
    }
    rev {
        additionalTags = emptyList()
    }
}

tasks.withType<BootBuildImage> {
    builder.set("paketobuildpacks/builder:tiny")
    environment.set(mapOf("BP_NATIVE_IMAGE" to "true"))
    imageName.set(fullImageName.plus(":latest"))
    tags.set(additionalTags)
    publish.set(isRegistryPublish)
    if(isRegistryPublish) {
        docker {
            publishRegistry {
                url.set(registryBaseUrl)
                username.set(registryUsername)
                password.set(registryPassword)
            }
        }
    }
}

/**
 * Change to true if you want to run app locally in native mode
 */
tasks.getByName<BootRun>("bootRun") {
    systemProperty("springAot", false)
}