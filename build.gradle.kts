plugins {
    kotlin("jvm") version "2.2.0-RC"
    id("com.github.johnrengelman.shadow") version "8.1.1" // Use the official shadow plugin
    id("xyz.jpenilla.run-paper") version "2.3.1"
}

group = "discord"
version = "1.0.0"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/") {
        name = "papermc-repo"
    }
    // Only needed if you use SNAPSHOTs
    // maven("https://oss.sonatype.org/content/repositories/snapshots/") {
    //     name = "ossrh-snapshots"
    // }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.5-R0.1-SNAPSHOT")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("space.arim.dazzleconf:dazzleconf-ext-snakeyaml:1.2.0-M2")
    compileOnly("org.yaml:snakeyaml:1.28")
}

val targetJavaVersion = 21
kotlin {
    jvmToolchain(targetJavaVersion)
}

tasks {
    build {
        dependsOn("shadowJar")
    }

    processResources {
        val props = mapOf("version" to version)
        inputs.properties(props)
        filteringCharset = "UTF-8"
        filesMatching("plugin.yml") {
            expand(props)
        }
    }

    shadowJar {
        // Relocate DazzleConf to avoid plugin conflicts
        relocate("space.arim.dazzleconf", "discord.qeid.superchatgames.shaded.dazzleconf")
    }

    runServer {
        minecraftVersion("1.21")
    }
}
