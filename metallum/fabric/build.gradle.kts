import quest.toybox.template.Constants

plugins {
    id("template-fabric")
}

Constants.setupForRelease(project, Constants.Metallum)

dependencies {
    implementation(project(path = ":options:fabric", configuration = "namedElements"))
}