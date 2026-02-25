plugins {
    id("buildsrc.convention.kotlin-jvm")
    id("buildsrc.convention.kotlin-spring")
    `java-library`
}

dependencies {
    implementation(libs.spring.aop)
    implementation(libs.aspectjweaver)
    implementation(libs.slf4j.api)
    implementation(libs.spring.boot.autoconfigure)
    implementation(kotlin("reflect"))
    compileOnly(libs.jakarta.servlet)
    implementation(project(":core"))
    implementation(project(":utils"))
    testImplementation(kotlin("test"))
}
