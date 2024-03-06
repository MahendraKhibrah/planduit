plugins {
    id("com.android.library")
    id("common-plugin")
}

android {
    namespace = "${ApplicationId.id}.common"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}
