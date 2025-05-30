import quest.toybox.template.Constants

plugins {
    id("template-platform")
    id("fabric-loom")
}

dependencies {
    minecraft("com.mojang:minecraft:${Constants.MINECRAFT_VERSION}")

    @Suppress("UnstableApiUsage")
    mappings(loom.layered {
        officialMojangMappings()
        parchment("org.parchmentmc.data:parchment-${Constants.PARCHMENT_MINECRAFT}:${Constants.PARCHMENT_RELEASE}@zip")
    })

    modImplementation(group = "net.fabricmc", name = "fabric-loader", version = Constants.FABRIC_LOADER_VERSION)
    modImplementation(group = "net.fabricmc.fabric-api", name = "fabric-api", version = Constants.FABRIC_API_VERSION)
    modImplementation(group = "net.fabricmc", name = "fabric-language-kotlin", version = Constants.FABRIC_KOTLIN_VERSION)
}

tasks.processResources {
    exclude("META-INF/accesstransformer.cfg")
}

