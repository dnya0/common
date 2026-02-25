plugins {
    id("buildsrc.convention.kotlin-jvm")
    id("buildsrc.convention.kotlin-spring")
    `java-library`
}

dependencies {
    implementation(libs.spring.context)
    implementation(libs.spring.boot.starter.data.redis)
    implementation(kotlin("reflect"))
    compileOnly(libs.spring.web)
    implementation(libs.spring.boot.autoconfigure)
    implementation(project(":utils"))
    implementation(project(":core"))
    testImplementation(kotlin("test"))
}
