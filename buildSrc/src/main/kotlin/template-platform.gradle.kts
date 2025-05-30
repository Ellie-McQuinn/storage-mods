import quest.toybox.template.Constants
import quest.toybox.template.Constants.FABRIC_API_VERSION
import quest.toybox.template.Constants.FABRIC_KOTLIN_VERSION
import quest.toybox.template.Constants.FABRIC_LOADER_VERSION
import quest.toybox.template.Constants.FL_MINECRAFT_CONSTRAINT
import quest.toybox.template.Constants.FML_CONSTRAINT
import quest.toybox.template.Constants.JAVA_VERSION
import quest.toybox.template.Constants.MINECRAFT_VERSION
import quest.toybox.template.Constants.NEOFORGE_KOTLIN_VERSION
import quest.toybox.template.Constants.NEOFORGE_VERSION
import quest.toybox.template.Constants.NF_MINECRAFT_CONSTRAINT
import quest.toybox.template.task.JsonProcessingReader
import kotlin.jvm.java

plugins {
    id("template-base")
}

val parents = listOf(parent?.findProject("common")!!)

for (parent in parents) {
    evaluationDependsOn(parent.path)
}

configurations {
    create("commonJava") { isCanBeResolved = true }
    create("commonKotlin") { isCanBeResolved = true }
    create("commonResources") { isCanBeResolved = true }
}

dependencies {
    for (parent in parents) {
        compileOnly(project(parent.path))

        "commonJava"(project(path = parent.path, configuration = "commonJava"))
        "commonKotlin"(project(path = parent.path, configuration = "commonKotlin"))
        "commonResources"(project(path = parent.path, configuration = "commonResources"))
    }
}

tasks {
    compileJava {
        configurations["commonJava"].also { dependsOn(it); source(it) }
    }

    compileKotlin {
        configurations["commonKotlin"].also { dependsOn(it); source(it) }
    }

    processResources {
        configurations["commonResources"].also {
            dependsOn(it)
            from(it) {
                exclude("fabric.mod.json")
            }
        }
    }
}


val constants = Constants.getConstants(project)

group = constants.group
version = constants.version

extensions.getByType(BasePluginExtension::class).archivesName.set("${constants.modId}-${project.name}-${MINECRAFT_VERSION}")

tasks.named("jar", Jar::class.java).configure {
    manifest {
        attributes(mapOf(
            "Specification-Title" to constants.modName,
            "Specification-Vendor" to constants.contributors.firstEntry().key,
            "Specification-Version" to archiveVersion,
            "Implementation-Title" to project.name,
            "Implementation-Version" to archiveVersion,
            "Implementation-Vendor" to constants.contributors.firstEntry().key,
            "Built-On-Minecraft" to MINECRAFT_VERSION
        ))
    }

    exclude("**/datagen/**")
    exclude(".cache/**")

    parent?.file("LICENSE")?.also { if (it.exists()) from(it) }
}

tasks.named("processResources", ProcessResources::class.java).configure {
    val replacements = mutableMapOf(
        "version" to version,

        "java_version" to JAVA_VERSION.asInt(),
        "minecraft_version" to MINECRAFT_VERSION,
        "fl_minecraft_constraint" to FL_MINECRAFT_CONSTRAINT,
        "nf_minecraft_constraint" to NF_MINECRAFT_CONSTRAINT,

        "fabric_loader_version" to FABRIC_LOADER_VERSION,
        "fabric_api_version" to FABRIC_API_VERSION,
        "fabric_kotlin_version" to FABRIC_KOTLIN_VERSION.substringBefore('+'),

        "fml_version_constraint" to FML_CONSTRAINT,
        "neoforge_version" to NEOFORGE_VERSION,
        "neoforge_kotlin_version" to NEOFORGE_KOTLIN_VERSION
    )

    inputs.properties(replacements)
    filesMatching(listOf("fabric.mod.json", "quilt.mod.json", "META-INF/neoforge.mods.toml", "*.mixins.json", "*.mcmeta")) {
        expand(replacements)
    }

    filesMatching(listOf("*.json", "*.mcmeta")) {
        filter(JsonProcessingReader::class.java)
    }
}