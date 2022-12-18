@Suppress("UnstableApiUsage")
dependencyResolutionManagement {

    repositories {
        gradlePluginPortal()
        maven { url = uri("https://repo.spring.io/release") }
        maven { url = uri("https://repo.spring.io/milestone") }
    }

    pluginManagement {
        repositories {
            gradlePluginPortal()
            maven { url = uri("https://repo.spring.io/release") }
            mavenCentral()
        }
    }
}

rootProject.name = "ecowatt-data-services"
include(":core")
include(":log-ecowatt-signals-task")
