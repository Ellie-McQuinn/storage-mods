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