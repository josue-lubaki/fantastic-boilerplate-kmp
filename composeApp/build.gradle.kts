import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.compose.ExperimentalComposeLibrary
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING
import org.jetbrains.kotlin.gradle.plugin.extraProperties
import java.util.Properties

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.konfig)
    kotlin("plugin.serialization")
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }
    
    jvm("desktop")

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    
    sourceSets {
        val desktopMain by getting
        
        androidMain.dependencies {
            implementation(libs.compose.ui)
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.ktor.client.okhttp)

            // accompanist - UI Controller
            implementation(libs.accompanist.systemUIController)

            // Koin
            implementation(libs.koin.android)


        }

        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutines.swing)
            implementation(libs.ktor.client.okhttp)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
            implementation(libs.components.resources)
        }

        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.material3)
            implementation(compose.materialIconsExtended)
            @OptIn(ExperimentalComposeLibrary::class)
            implementation(compose.components.resources)

            // ktor
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)

            implementation("co.touchlab:stately-common:2.0.5")

            // Koin
            api(libs.koin.core)
            api(libs.koin.compose)

            // logger
            implementation(libs.napier)

            // datetime
            implementation(libs.datetime)

            // voyager
            implementation(libs.voyager.navigation)
            implementation(libs.voyager.koin)
            implementation(libs.voyager.transitions)
            implementation(libs.voyager.tabNavigator)
            implementation(libs.voyager.bottomSheetNavigator)

            // preferences
            implementation(libs.preferences)
            implementation(libs.preferences.coroutines)

            // window-size
            implementation(libs.window.size)

            // serialization
            implementation(libs.kotlinx.serialization.json)
        }
    }
}

android {
    namespace = "com.alithya.boilerplate"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "com.alithya.boilerplate"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    dependencies {
        debugImplementation(libs.compose.ui.tooling)
    }
}

compose.desktop {
    application {
        mainClass = "com.alithya.boilerplate.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.alithya.boilerplate"
            packageVersion = "1.0.0"
        }
    }
}

buildkonfig {
    packageName = "com.alithya.boilerplate"

    val properties = Properties()

    val localPropertiesFile = project.rootProject.file("local.properties")
    if(localPropertiesFile.exists()){
        localPropertiesFile.inputStream().use { properties.load(it) }
    }

    defaultConfigs {
        buildConfigField(STRING, "BASE_URL", properties.getProperty("BASE_URL"))
        buildConfigField(STRING, "GRANT_TYPE", properties.getProperty("GRANT_TYPE"))
        buildConfigField(STRING, "CLIENT_ID", properties.getProperty("CLIENT_ID"))
        buildConfigField(STRING, "CLIENT_SECRET", properties.getProperty("CLIENT_SECRET"))
    }
}

// fix for Android Studio canary latest version
task("testClasses").doLast {
    println("This is a dummy testClasses task")
}

task("deleteAttributes").doFirst {
    project.exec {
        commandLine("xattr", "-cr", ".")
    }
}