import top.laoshuzi.dependencies.AndroidBuildConfig
import top.laoshuzi.dependencies.deps.*

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

val signKeyAlias: String by project
val signKeyPassword: String by project
val signStoreFile: String by project
val signStorePassword: String by project

android {
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    compileSdkVersion(AndroidBuildConfig.target_sdk)
    defaultConfig {
        applicationId = AndroidBuildConfig.application_id
        minSdkVersion(AndroidBuildConfig.min_sdk)
        targetSdkVersion(AndroidBuildConfig.target_sdk)
        versionCode = AndroidBuildConfig.version_code
        versionName = AndroidBuildConfig.version_name
        testInstrumentationRunner = AndroidBuildConfig.test_instrumentation_runner
        consumerProguardFiles(AndroidBuildConfig.consumer_file)
        multiDexEnabled = true
    }
    lintOptions {
        isAbortOnError = false
    }
    signingConfigs {
        create("release") {
            keyAlias = signKeyAlias
            keyPassword = signKeyPassword
            storeFile = file(signStoreFile)
            storePassword = signStorePassword
        }
    }
    buildTypes {
        getByName("release") {
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                AndroidBuildConfig.proguard_file
            )
        }
        getByName("debug") {
            signingConfig = signingConfigs.getByName("release")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                AndroidBuildConfig.proguard_file
            )
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.aar"))))

    implementation(project(":calendarview"))

    //kotlin
    implementation(deps(Kotlin.stdlib_jdk8))

    //common
    implementation(deps(Common.core))

    //test
//    testImplementation(deps(Junit.junit))
//    androidTestImplementation(deps(AndroidX.test_runner))
//    androidTestImplementation(deps(AndroidX.test_espresso))


    //component
    implementation(deps(AndroidX.recyclerview))
    implementation(deps(AndroidX.appcompat))

    //glide
    implementation(deps(Glide.glide))
    kapt(deps(Glide.glide_compiler))
}
