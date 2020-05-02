const val propertiesDir = "signin/properties"

const val coroutines_version = "1.3.5-native-mt"
const val kotlin_version = "1.3.71"
const val cocoapods_version = "0.6"
const val firebase_auth_version = "19.3.0"

object App {
    const val minSdkVersion = 23
    const val targetSdkVersion = 29
    const val versionCode = 1
    const val versionName = "0.0.1"
    const val testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
}

object Common {
    const val minSdkVersion = 23
    const val targetSdkVersion = 29
    const val versionCode = 1
    const val versionName = "0.0.1"
    const val testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
}

object Dependencies {
    object Android {
        // Android
        const val appCompat = "androidx.appcompat:appcompat:1.1.0"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:1.1.3"
        const val recycler = "androidx.recyclerview:recyclerview:1.2.0-alpha01"
        const val material = "com.google.android.material:material:1.2.0-alpha03"
        // Firebase
        const val firebaseCore = "com.google.firebase:firebase-core:16.0.8"

        // Coroutines
        const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version"

        object Test {
            const val junit = "junit:junit:4.12"
            const val runner = "com.android.support.test:runner:1.0.2"
        }
    }

    object Root {
        const val android = "com.android.tools.build:gradle:3.5.3"
        const val googlePlay = "com.google.gms:google-services:4.2.0"
        const val cocoapods = "co.touchlab:kotlinnativecocoapods:$cocoapods_version"
    }

    object Common {
        object Main {
            const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core-common:$coroutines_version"
        }

        object Android {
            const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version"
            const val firebaseAuth = "com.google.firebase:firebase-auth:$firebase_auth_version"
        }

        object Native {
            const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core-native:$coroutines_version"
        }
    }
}
