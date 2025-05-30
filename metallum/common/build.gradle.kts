import quest.toybox.template.Constants

plugins {
    id("template-common")
}


dependencies {
    compileOnly(project(path = ":options:common"))
}