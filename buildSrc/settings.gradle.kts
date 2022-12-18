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
