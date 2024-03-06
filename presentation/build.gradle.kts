plugins {
    id("com.android.library")
    id("common-plugin")
}

android {
    namespace = "${ApplicationId.id}.presentation"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    implementation(project(":common"))
    implementation(project(":domain"))
}
