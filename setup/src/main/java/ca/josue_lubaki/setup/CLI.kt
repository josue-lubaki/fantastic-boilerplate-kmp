package ca.josue_lubaki.setup

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.arguments.default
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.prompt
import com.github.ajalt.clikt.parameters.types.file
import java.io.File

/**
 * created by Josue Lubaki
 * date : 2024-02-05
 * version : 1.0.0
 */

class CLI : CliktCommand(){

    private val actualPackageName = "com.alithya.boilerplate"
    private val rootProjectName = "BoilerPlate"

    private val composeAppBuildGradle by argument().file().default(File("composeApp/build.gradle.kts"))
    private val settingsGradle by argument().file().default(File("settings.gradle.kts"))

    private val appName : String by option().prompt("Enter the name of your app (example : MyAwesomeApp)")
    private val packageName : String by option().prompt("Enter the package name of your app (example : com.example.app)")

    override fun run() {
        echo("Step 1 : composeApp changes...")
        val composeAppBuildGradleContent = composeAppBuildGradle.readText()
        composeAppBuildGradle.delete()
        composeAppBuildGradle.createNewFile()
        composeAppBuildGradle.writeText(composeAppBuildGradleContent.replace(actualPackageName, packageName))
        echo("Step 1 : DONE")

        setupComposeApp(packageName)

        setupSettingsGradle(appName)

        GitAddCommitCommand("test commit message, setup app").main(emptyArray())

        echo("Processing --> DONE")
    }

    private fun setupComposeApp(packageName: String) {
        echo("Step 2 : setupComposeApp changes...")
        echo("Processing : androidApp changes...")
        val oldAndroidMain = File("composeApp/src/androidMain/kotlin/${actualPackageName.replace(".", "/")}")
        val androidMainComposeApp = File("composeApp/src/androidMain/kotlin/${packageName.replace(".", "/")}")
        androidMainComposeApp.mkdirs()
        oldAndroidMain.copyRecursively(androidMainComposeApp, overwrite = false)
        oldAndroidMain.deleteRecursively()
        androidMainComposeApp
            .walkTopDown()
            .filter { it.isFile }
            .filter { !it.name.endsWith(".png") }
            .filter { !it.name.endsWith(".webp") }
            .forEach {
                val content = it.readText()
                it.delete()
                it.createNewFile()
                it.writeText(content.replace(actualPackageName, packageName))
            }
        echo("Processing : androidApp done")

        echo("Processing : commonMainApp changes...")
        val oldCommonMain = File("composeApp/src/commonMain/kotlin/${actualPackageName.replace(".", "/")}")
        val commonMain = File("composeApp/src/commonMain/kotlin/${packageName.replace(".", "/")}")
        commonMain.mkdirs()
        oldCommonMain.copyRecursively(commonMain, overwrite = false)
        oldCommonMain.deleteRecursively()
        commonMain
            .walkTopDown()
            .filter { it.isFile }
            .filter { !it.name.endsWith(".png") }
            .filter { !it.name.endsWith(".webp") }
            .forEach {
                val content = it.readText()
                it.delete()
                it.createNewFile()
                it.writeText(content.replace(actualPackageName, packageName))
            }
        echo("Processing : commonMainApp done")

        echo("Processing : desktopMainApp changes...")
        val oldDesktopMain = File("composeApp/src/desktopMain/kotlin/${actualPackageName.replace(".", "/")}")
        val desktopMain = File("composeApp/src/desktopMain/kotlin/${packageName.replace(".", "/")}")
        desktopMain.mkdirs()
        oldDesktopMain.copyRecursively(desktopMain, overwrite = false)
        oldDesktopMain.deleteRecursively()
        desktopMain
            .walkTopDown()
            .filter { it.isFile }
            .filter { !it.name.endsWith(".png") }
            .filter { !it.name.endsWith(".webp") }
            .forEach {
                val content = it.readText()
                it.delete()
                it.createNewFile()
                it.writeText(content.replace(actualPackageName, packageName))
            }
        echo("Processing : desktopMainApp done")

        echo("Processing : iosMainApp changes...")
        val oldIosMain = File("composeApp/src/iosMain/kotlin/${actualPackageName.replace(".", "/")}")
        val iosMain = File("composeApp/src/iosMain/kotlin/${packageName.replace(".", "/")}")
        iosMain.mkdirs()
        oldIosMain.copyRecursively(iosMain, overwrite = false)
        oldIosMain.deleteRecursively()
        iosMain
            .walkTopDown()
            .filter { it.isFile }
            .filter { !it.name.endsWith(".png") }
            .filter { !it.name.endsWith(".webp") }
            .forEach {
                val content = it.readText()
                it.delete()
                it.createNewFile()
                it.writeText(content.replace(actualPackageName, packageName))
            }
        echo("Processing : iosMainApp done")
        echo("Step 2 --> DONE")
    }

    private fun setupSettingsGradle(appName: String) {
        echo("Step 3 : setupSettingsGradle changes...")
        val settingsGradleContent = settingsGradle.readText()
        settingsGradle.delete()
        settingsGradle.createNewFile()
        settingsGradle.writeText(settingsGradleContent.replace(rootProjectName, appName))
        echo("Step 3 --> DONE")
    }
}


class GitAddCommitCommand(private val message: String) : CliktCommand() {
    override fun run() {
        val gitAdd = ProcessBuilder("git", "add", ".")
        val gitCommit = ProcessBuilder("git", "commit", "-m", message)

        val gitAddProcess = gitAdd
            .redirectOutput(ProcessBuilder.Redirect.INHERIT)
            .redirectError(ProcessBuilder.Redirect.INHERIT)
            .start()

        val addExitCode = gitAddProcess.waitFor()
        if (addExitCode != 0) {
            echo(message = "git add failed with exit code : $addExitCode")
            return
        }
//
//        val gitCommitProcess = gitCommit
//            .redirectOutput(ProcessBuilder.Redirect.INHERIT)
//            .redirectError(ProcessBuilder.Redirect.INHERIT)
//            .start()
//
//        val commitExitCode = gitCommitProcess.waitFor()

        echo(message = "git add success")
    }
}