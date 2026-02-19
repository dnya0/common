plugins {
    id("buildsrc.convention.kotlin-jvm")
    id("buildsrc.convention.kotlin-spring")
    `java-library`
}

dependencies {
    api(libs.spring.aop)
    api(libs.aspectjweaver)
    implementation(libs.spring.boot.autoconfigure)
    compileOnly(libs.jakarta.servlet)
    implementation(project(":core"))
    implementation(project(":utils"))
    testImplementation(kotlin("test"))
}
