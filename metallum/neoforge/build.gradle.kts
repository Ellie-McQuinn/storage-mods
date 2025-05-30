import quest.toybox.template.Constants

plugins {
    id("template-neoforge")
}

dependencies {
    compileOnly(project(path = ":options:common"))
    implementation(project(path = ":options:neoforge"))
}