plugins {
    id("buildsrc.convention.kotlin-jvm")
    id("buildsrc.convention.kotlin-spring")
    id("buildsrc.convention.kotlin-publishing")
    `java-library`
}

dependencies {
    implementation(project(":utils"))
    implementation(libs.spring.context)
    implementation(libs.spring.tx)
    implementation(libs.spring.data.commons)
    implementation(libs.spring.web)
    implementation(libs.spring.boot.autoconfigure)
    testImplementation(kotlin("test"))
}
