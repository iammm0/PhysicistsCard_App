enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()

        maven {
            url = uri("https://mirrors.aliyun.com/maven/repository/")
        }
    }
}

dependencyResolutionManagement {
    repositories {
        maven {
            url = uri("https://mirrors.aliyun.com/maven/repository/")
        }

        google()
        mavenCentral()
    }
}

rootProject.name = "PhysicistsCard"
include(":androidApp")
include(":shared")