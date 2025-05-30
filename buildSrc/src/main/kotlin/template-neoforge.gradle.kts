import quest.toybox.template.Constants

plugins {
    id("template-platform")
    id("net.neoforged.moddev")
}

neoForge {
    version = Constants.NEOFORGE_VERSION

    parchment {
        minecraftVersion = Constants.PARCHMENT_MINECRAFT
        mappingsVersion = Constants.PARCHMENT_RELEASE
    }
}

repositories {
    exclusiveContent {
        forRepository {
            maven {
                name = "Kotlin for Forge Maven"
                url = uri("https://thedarkcolour.github.io/KotlinForForge/")
            }
        }
        filter {
            includeGroup("thedarkcolour")
        }
    }
}

dependencies {
    implementation("thedarkcolour:kotlinforforge-neoforge:${Constants.NEOFORGE_KOTLIN_VERSION}")
}

tasks.processResources {
    exclude("*.accesswidener")
}
