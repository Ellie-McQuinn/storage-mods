plugins {
    id("template-neoforge")
}

neoForge {
    runs {
        val common = findProject(":options:common")!!

        named("data") {
            programArguments.addAll(
                "--existing", common.file("src/main/resources").absolutePath
            )
        }

        named("commonData") {
            programArguments.addAll(
                "--existing", common.file("src/main/resources").absolutePath
            )
        }
    }
}

dependencies {
    compileOnly(project(path = ":options:common"))
    implementation(project(path = ":options:neoforge"))
}