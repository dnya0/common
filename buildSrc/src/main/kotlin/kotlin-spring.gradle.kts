// Convention plugin for Spring-based modules
package buildsrc.convention

plugins {
    kotlin("jvm")
    kotlin("plugin.spring") // Makes classes open for Spring proxying
}

kotlin {
    jvmToolchain(21)
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}
