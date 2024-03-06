plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}
gradlePlugin {
    plugins {
        register("common-plugin") {
            id = "common-plugin"
            implementationClass = "common.CommonPlugin"
        }
    }
}

dependencies {
    implementation("com.android.tools.build:gradle:8.2.2")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.0")
}