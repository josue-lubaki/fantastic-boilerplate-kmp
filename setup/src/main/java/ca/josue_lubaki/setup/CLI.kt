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
    companion object {
        const val TEMPLATE_APP_NAME = "BoilerPlate"
    }

    private val actualPackageName = "com.alithya.boilerplate"
    private val rootProjectName = "BoilerPlate"

    private val composeAppModule by argument().file().default(File("composeApp"))
    private val composeAppBuildGradle by argument().file().default(File("composeApp/build.gradle.kts"))
    private val settingsGradle by argument().file().default(File("settings.gradle.kts"))

    private val appName : String by option().prompt("Enter the name of your app (example : MyAwesomeApp)")
    private val packageName : String by option().prompt("Enter the package name of your app (example : com.example.app)")

    override fun run() {
        val composeAppBuildGradleContent = composeAppBuildGradle.readText()
        composeAppBuildGradle.delete()
        composeAppBuildGradle.createNewFile()
        composeAppBuildGradle.writeText(composeAppBuildGradleContent.replace(actualPackageName, packageName))

        echo("Processing : composeApp changes...")

//        setupAppName()
        setupComposeApp(packageName)
        setupSettingsGradle(appName)

        GitAddCommitCommand("test commit message, setup app").main(emptyArray())
    }

    private fun setupComposeApp(packageName: String) {
        val androidMainComposeApp = File("composeApp/src/androidMain/kotlin/${packageName.replace(".", "/")}")
        androidMainComposeApp.mkdirs()
        composeAppModule.copyRecursively(androidMainComposeApp, overwrite = true)
        composeAppModule.deleteRecursively()

        val commonMain = File("composeApp/src/commonMain/kotlin/${packageName.replace(".", "/")}")
        commonMain.mkdirs()
        composeAppModule.copyRecursively(commonMain, overwrite = true)
        composeAppModule.deleteRecursively()

        val desktopMain = File("composeApp/src/desktopMain/kotlin/${packageName.replace(".", "/")}")
        desktopMain.mkdirs()
        composeAppModule.copyRecursively(desktopMain, overwrite = true)
        composeAppModule.deleteRecursively()

        val iosMain = File("composeApp/src/iosMain/kotlin/${packageName.replace(".", "/")}")
        iosMain.mkdirs()
        composeAppModule.copyRecursively(iosMain, overwrite = true)
        composeAppModule.deleteRecursively()
    }

    private fun setupSettingsGradle(appName: String) {
        val settingsGradleContent = settingsGradle.readText()
        settingsGradle.delete()
        settingsGradle.createNewFile()
        settingsGradle.writeText(settingsGradleContent.replace(rootProjectName, appName))
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