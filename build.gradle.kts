import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

buildscript {
    repositories {
        google()
        jcenter()
        mavenCentral()
        maven(url = "https://kotlin.bintray.com/kotlinx")
        maven(url = "https://dl.bintray.com/touchlabpublic/kotlin")
    }
    dependencies {
        classpath(Dependencies.Root.android)
        classpath(Dependencies.Root.googlePlay)
        classpath(Dependencies.Root.cocoapods)
        classpath(kotlin("gradle-plugin", kotlin_version))
    }
}

allprojects {

    val localProperties = gradleLocalProperties(rootDir)

    repositories {
        google()
        jcenter()
        mavenCentral()
        maven(url = "https://kotlin.bintray.com/kotlinx")
        maven(url = "https://dl.bintray.com/touchlabpublic/kotlin")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
