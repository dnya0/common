plugins {
    id("buildsrc.convention.kotlin-jvm")
    id("buildsrc.convention.kotlin-spring")
    id("buildsrc.convention.kotlin-publishing")
    `java-library`
}

dependencies {
    implementation(project(":core"))
    implementation(libs.spring.context)
    implementation(libs.spring.webflux)
    compileOnly(libs.jakarta.validation)
    implementation(libs.spring.boot.autoconfigure)
    testImplementation("org.springframework.boot:spring-boot-starter-test:4.0.5")
    testImplementation(kotlin("test"))
}
