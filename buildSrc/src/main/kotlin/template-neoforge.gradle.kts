import quest.toybox.template.Constants

plugins {
    id("template-platform")
    id("net.neoforged.moddev")
}

val constants = Constants.getConstants(project)
val common = parent?.findProject("common")!!
val projectName = constants.modName.split(" ").last()

neoForge {
    version = Constants.NEOFORGE_VERSION

    accessTransformers.from(common.neoForge.accessTransformers.files)

    parchment {
        minecraftVersion = Constants.PARCHMENT_MINECRAFT
        mappingsVersion = Constants.PARCHMENT_RELEASE
    }

    runs {
        configureEach {
            systemProperty("neoforge.enabledGameTestNamespaces", constants.modId)
        }

        create("client") {
            client()
            ideName = "$projectName N-Client (${project.path})"
        }

        create("server") {
            server()
            ideName = "$projectName N-Server (${project.path})"
        }

        create("data") {
            data()
            ideName = "$projectName N-Data (${project.path})"

            programArguments.addAll(
                "--mod", constants.modId,
                "--output", file("src/generated/resources").absolutePath,
                "--existing", common.file("src/main/resources").absolutePath,
                "--all"
            )
        }

        create("commonData") {
            data()
            ideName = "$projectName C-Data (${project.path})"

            programArguments.addAll(
                "--mod", constants.modId,
                "--output", common.file("src/generated/resources").absolutePath,
                "--existing", common.file("src/main/resources").absolutePath,
                "--all"
            )

            systemProperty("${constants.modId}.datagen.common", "true")
        }
    }

    mods {
        create(constants.modId) {
            sourceSet(sourceSets.main.get())
        }
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

sourceSets.main {
    resources.srcDirs("src/generated/resources")
}

tasks.processResources {
    exclude("*.accesswidener")
}
