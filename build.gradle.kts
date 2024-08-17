plugins {
    id("com.github.johnrengelman.shadow") version "8.1.1"
    kotlin("jvm") version "1.9.22"
    `maven-publish`
}

group = "world.estaria"
version = "1.0.1"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly(kotlin("stdlib"))

    // avionik dependencies
    compileOnly("world.avionik:database-simplified-kit:1.1.0")
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/estaria-world/uuid-cacher")
            credentials {
                username = project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME")
                password = project.findProperty("gpr.key") as String? ?: System.getenv("TOKEN")
            }
        }
    }
    publications {
        register<MavenPublication>("gpr") {
            from(components["java"])
        }
    }
}