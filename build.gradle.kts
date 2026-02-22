allprojects {
    group = "com.github.dnya0"
    version = project.findProperty("version") as String? ?: "0.1.0"

    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "buildsrc.convention.kotlin-publishing")
}
