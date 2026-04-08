plugins {
    id("buildsrc.convention.kotlin-jvm")
    id("buildsrc.convention.kotlin-spring")
    id("buildsrc.convention.kotlin-publishing")
    `java-library`
    alias(libs.plugins.kotlinPluginJpa)
}

dependencies {
    implementation(project(":core"))
    implementation(libs.spring.data.jpa)
    implementation(libs.hibernate.core)
    implementation(libs.jakarta.persistence)
    implementation(libs.spring.aspects)
    implementation(libs.spring.boot.autoconfigure)
    testImplementation(kotlin("test"))
}
repositories {
    mavenCentral()
}
