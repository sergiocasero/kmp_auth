plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    id("com.google.gms.google-services")
}

repositories {
    maven(url = "https://jitpack.io")
    flatDir {
        dirs = mutableSetOf(File("libs"))
    }
}

android {
    compileSdkVersion(App.targetSdkVersion)

    defaultConfig {
        minSdkVersion(App.minSdkVersion)
        targetSdkVersion(App.targetSdkVersion)
        versionCode = App.versionCode
        versionName = App.versionName
        testInstrumentationRunner = App.testInstrumentationRunner
        applicationId = "com.sergiocasero.authsample"

        multiDexEnabled = true
    }

    flavorDimensions("version")



    buildTypes {

    }

    packagingOptions {
        exclude("META-INF/*")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    lintOptions {
        isAbortOnError = false
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar", "*.aar"))))
    implementation(kotlin("stdlib-jdk7", org.jetbrains.kotlin.config.KotlinCompilerVersion.VERSION))

    implementation(project(":commonsample"))
    implementation(project(":common"))

    implementation(Dependencies.Android.appCompat)
    implementation(Dependencies.Android.constraintLayout)
    implementation(Dependencies.Android.recycler)
    implementation(Dependencies.Android.material)
    implementation(Dependencies.Android.coroutinesAndroid)

    // Legacy data dependencies, remove this when the project is migrated
    androidTestImplementation(Dependencies.Android.Test.runner)
}

tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class.java) {
    sourceCompatibility = JavaVersion.VERSION_1_8.toString()
    targetCompatibility = JavaVersion.VERSION_1_8.toString()

    kotlinOptions {
        jvmTarget = "1.8"
    }
}
