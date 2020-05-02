import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    id("co.touchlab.native.cocoapods")
    id("com.android.library")
}

android {
    compileSdkVersion(Common.targetSdkVersion)

    defaultConfig {
        minSdkVersion(Common.minSdkVersion)
        targetSdkVersion(Common.targetSdkVersion)
        versionCode = Common.versionCode
        versionName = Common.versionName
        testInstrumentationRunner = Common.testInstrumentationRunner
    }

    flavorDimensions("version")
}

dependencies {
    // Nothing to import
}

kotlin {
    android()
    // This is for iPhone emulator
    // Switch here to iosArm64 (or iosArm32) to build library for iPhone device
    val onPhone = System.getenv("SDK_NAME")?.startsWith("iphoneos") ?: false
    if (onPhone) {
        iosArm64("ios")
    } else {
        iosX64("ios")
    }

    targets.getByName<KotlinNativeTarget>("ios").compilations["main"].kotlinOptions.freeCompilerArgs +=
        listOf("-Xobjc-generics", "-Xg0")

    version = "1.1"

    sourceSets {
        all {
            languageSettings.apply {
                useExperimentalAnnotation("kotlinx.coroutines.ExperimentalCoroutinesApi")
            }
        }
    }

    sourceSets["commonMain"].dependencies {
        implementation(Dependencies.Common.Main.coroutines)
        implementation(kotlin("stdlib-common"))
        implementation(project(":common"))
    }

    sourceSets["commonTest"].dependencies {
        implementation(kotlin("test-common"))
        implementation(kotlin("test-annotations-common"))
    }

    sourceSets["androidMain"].dependencies {
        implementation(Dependencies.Common.Android.firebaseAuth)
        implementation(Dependencies.Common.Android.coroutines)

        implementation(kotlin("stdlib"))
    }

    sourceSets["androidTest"].dependencies {
        implementation(kotlin("test"))
        implementation(kotlin("test-junit"))
    }

    sourceSets["iosMain"].dependencies {
        implementation(Dependencies.Common.Native.coroutines)
        implementation(kotlin("stdlib"))
    }
    sourceSets["iosTest"].dependencies {
    }

    cocoapodsext {
        summary = "Common library for using firebase auth"
        homepage = "TODO"
        isStatic = false

        // pod("FirebaseCore")
        pod("FirebaseAuth")
    }
}

