import quest.toybox.template.Constants

plugins {
    id("template-fabric")
}

dependencies {
    compileOnly(project(path = ":options:common"))
    implementation(project(path = ":options:fabric", configuration = "namedElements"))
}