plugins {
    id("buildsrc.convention.kotlin-jvm")
    id("buildsrc.convention.kotlin-spring")
    id("buildsrc.convention.kotlin-publishing")
    `java-library`
}

dependencies {
    api(project(":utils"))
    api(libs.spring.context)
    api(libs.spring.tx)
    api(kotlin("reflect"))
    compileOnly(libs.jakarta.servlet)
    compileOnly(libs.jakarta.persistence)
    compileOnly(libs.hibernate.core)
    compileOnly(libs.spring.data.jpa)
    compileOnly(libs.spring.web)
    implementation(libs.spring.boot.autoconfigure)
    testImplementation(kotlin("test"))
}
