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

rootProject.name = "ecowatt-data-jobs"
include(":core")
include(":log-ecowatt-signals-task")
include(":send-ecowatt-email-task")
