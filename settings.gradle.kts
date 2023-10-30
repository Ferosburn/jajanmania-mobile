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
include(":customer:auth")
include(":customer:home")
include(":customer:profile")
include(":vendor:home")
include(":vendor:transaction")
include(":vendor:account")
include(":core:data")
include(":core:domain")
include(":vendor:ewallet")
include(":common")
include(":vendor:shop")
include(":customer:ewallet")
include(":vendor:cashier")
include(":customer:transaction")
include(":navigation")
include(":vendor:auth")
include(":customer:subscription")
include(":customer:transaction")
include(":navigation")
include(":vendor:auth")
include(":customer:payment")
include(":customer:vendor")
include(":customer:cart")
