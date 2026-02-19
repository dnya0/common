plugins {
    id("buildsrc.convention.kotlin-jvm")
    id("buildsrc.convention.kotlin-spring")
    `java-library`
}

dependencies {
    api(libs.spring.context)
    api(libs.spring.tx)
    api(kotlin("reflect"))
    compileOnly(libs.jakarta.servlet)
    compileOnly(libs.spring.data.jpa)
    implementation(libs.spring.boot.autoconfigure)
    implementation(project(":utils"))
    testImplementation(kotlin("test"))
}
