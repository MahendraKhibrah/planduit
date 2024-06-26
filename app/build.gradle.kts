import dagger.hilt.android.plugin.HiltExtension

plugins {
    id("com.android.application")
    id("kotlin-android")

    id("com.google.dagger.hilt.android")
    kotlin("kapt")
    id("com.google.gms.google-services")
    id("common-plugin")

}

the<HiltExtension>().enableAggregatingTask = false

android {
    namespace = "${ApplicationId.id}.app"
    defaultConfig {
        applicationId = "${ApplicationId.id}"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        named("debug") {
            isDebuggable = true
        }

        named("release") {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true

            applicationIdSuffix = ".release"

            proguardFiles(
                android.getDefaultProguardFile("proguard-android.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(project(":common"))
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":presentation"))
}