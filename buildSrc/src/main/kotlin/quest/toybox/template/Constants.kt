package quest.toybox.template

import org.gradle.api.Project
import org.gradle.jvm.toolchain.JavaLanguageVersion

object Constants {
    const val GROUP = "quest.toybox.storage"

    val JAVA_VERSION = JavaLanguageVersion.of(21)
    const val JETBRAIN_ANNOTATIONS_VERSION = "24.1.0"

    const val MIXIN_VERSION = "0.8.5"
    const val MIXIN_EXTRAS_VERSION = "0.3.5"

    const val MINECRAFT_VERSION = "1.21.1"
    const val FL_MINECRAFT_CONSTRAINT = "1.21.1"
    const val NF_MINECRAFT_CONSTRAINT = "[1.21.1]"

    // https://parchmentmc.org/docs/getting-started#choose-a-version/
    const val PARCHMENT_MINECRAFT = "1.21.1"
    const val PARCHMENT_RELEASE = "2024.11.17"

    // https://fabricmc.net/develop/
    const val FABRIC_API_VERSION = "0.110.0+1.21.1"
    const val FABRIC_KOTLIN_VERSION = "1.13.0+kotlin.2.1.0"
    const val FABRIC_LOADER_VERSION = "0.16.10"

    const val NEOFORM_VERSION = "1.21.1-20240808.144430" // // https://projects.neoforged.net/neoforged/neoform/
    const val NEOFORGE_VERSION = "21.1.165" // https://projects.neoforged.net/neoforged/neoforge/
    const val NEOFORGE_KOTLIN_VERSION = "5.7.0"
    const val FML_CONSTRAINT = "[4,)" // https://projects.neoforged.net/neoforged/fancymodloader/

    fun injectMajor(minecraft: String, mod: String) = minecraft.split(".").let {
        "${it[1]}${it[2].padStart(2, '0')}.${mod}"
    }

    interface ConstantsHolder {
        val group: String
        val version: String
        val modId: String
        val modName: String

        val contributors: LinkedHashMap<String, String>
    }

    fun getConstants(project: Project): ConstantsHolder {
        return if (project.path.startsWith(":options:")) { Options }
        else if (project.path.startsWith(":metallum:")) { Metallum }
        else { throw IllegalArgumentException("Unknown project: ${project.path}") }
    }

    object Options : ConstantsHolder {
        override val group: String = GROUP
        override val modId: String = "storageoptions"
        override val modName: String = "Storage Options"
        override val version: String = injectMajor(MINECRAFT_VERSION, "0.5.0-beta.1")

        override val contributors: LinkedHashMap<String, String> = linkedMapOf(
            "Toybox System" to "Project Owners",
            "mpustovoi" to "Translator"
        )
    }

    object Metallum : ConstantsHolder {
        override val group: String = GROUP
        override val modId: String = "storagemetallum"
        override val modName: String = "Storage Metallum"
        override val version: String = injectMajor(MINECRAFT_VERSION, "0.1.0-alpha.1")

        override val contributors: LinkedHashMap<String, String> = linkedMapOf(
            "Toybox System" to "Project Owners"
        )
    }

    // Natura?
}