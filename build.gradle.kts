plugins {
    id("java-library")
    id("io.freefair.lombok") version "6.5.0.3"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "org.example"
version = "1.0-SNAPSHOT"

allprojects {
    apply(plugin = "java-library")
    apply(plugin = "io.freefair.lombok")
    apply(plugin = "com.github.johnrengelman.shadow")
    repositories {
        mavenCentral()
        maven("https://jitpack.io")
        maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/") // Spigot API
    }

    dependencies {
        compileOnly("org.jetbrains:annotations:23.0.0")
    }
}

configure(allprojects - project(":core")) {
    dependencies.api(project(":core"))
}


dependencies {

}
