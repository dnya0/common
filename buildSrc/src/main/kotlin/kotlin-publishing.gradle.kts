// Convention plugin for Maven publishing
package buildsrc.convention

plugins {
    `java-library`
    `maven-publish`
}

java {
    withSourcesJar()
    withJavadocJar()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
            
            pom {
                name.set(project.name)
                description.set("Common library for Spring Boot applications")
                url.set("https://github.com/dnya0/common")
                
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }
                
                developers {
                    developer {
                        id.set("dnya0")
                        name.set("dnya0")
                        email.set("annay01794@gmail.com")
                    }
                }
                
                scm {
                    connection.set("scm:git:git://github.com/dnya0/common.git")
                    developerConnection.set("scm:git:ssh://github.com/dnya0/common.git")
                    url.set("https://github.com/dnya0/common")
                }
            }
        }
    }
    
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/dnya0/common")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
}
