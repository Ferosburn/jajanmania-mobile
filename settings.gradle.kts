pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Jajan Mania"
include(":app")
include(":ui")
include(":customer:home")
include(":customer:profile")
include(":vendor:home")
include(":vendor:transaction")
include(":vendor:account")
