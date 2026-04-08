dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositories {
        mavenCentral()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}


rootProject.name = "common"

include("utils")
include("core")
include("webmvc")
include("webflux")
include("jpa")
include("core-aop")
include("redis")
include("starter")
