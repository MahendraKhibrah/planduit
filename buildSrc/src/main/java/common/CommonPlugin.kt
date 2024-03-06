package common

import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.plugin.KaptExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class CommonPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.configurePlugins()
        target.commonDependenciesSetup()
        target.configureKapt()
        target.configureAndroid()
    }

    private fun Project.configurePlugins() {
        plugins.apply("kotlin-android")
        plugins.apply("com.google.dagger.hilt.android")
        plugins.apply("kotlin-kapt")
    }

    private fun Project.configureKapt() {
        extensions.getByType(KaptExtension::class.java).run {
            this.correctErrorTypes = true
        }
    }

    private fun Project.configureAndroid() {
        extensions.getByType(BaseExtension::class.java).run {
            compileSdkVersion(Versions.androidCompileSdkVersion)

            defaultConfig {
                minSdk = Versions.androidMinSdkVersion
                targetSdk = Versions.androidTargetSdkVersion

                versionCode = Versions.versionCode
                versionName = Versions.versionName
            }

            tasks.withType<KotlinCompile> {
                kotlinOptions {
                    jvmTarget = "1.8"
                }
            }

            buildTypes {
                getByName("release") {
                    isMinifyEnabled = false
                }
            }

            buildFeatures.run {
                viewBinding {
                    enable = true
                }
                compose = true
            }

            composeOptions {
                kotlinCompilerExtensionVersion = "1.5.2"
            }

            packagingOptions {
                resources {
                    excludes.add("/META-INF/{AL2.0,LGPL2.1}")
                }
            }

            compileOptions {
                sourceCompatibility(JavaVersion.VERSION_1_8)
                targetCompatibility(JavaVersion.VERSION_1_8)
            }
        }
    }

}