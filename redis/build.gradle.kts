plugins {
    id("buildsrc.convention.kotlin-jvm")
    id("buildsrc.convention.kotlin-spring")
    `java-library`
}

dependencies {
    api(libs.spring.context)
    api(libs.spring.boot.starter.data.redis)
    api(kotlin("reflect"))
    compileOnly(libs.spring.web)
    implementation(libs.spring.boot.autoconfigure)
    implementation(project(":utils"))
    implementation(project(":core"))
    testImplementation(kotlin("test"))
}
