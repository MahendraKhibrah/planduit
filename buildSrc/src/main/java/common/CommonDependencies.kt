package common

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import utils.*

internal fun Project.commonDependenciesSetup() {
    dependencies {
        // Main
        implementation(MainLibraries.androidCoreKtx)
        implementation(MainLibraries.lifecycleRuntimeKtx)
        implementation(MainLibraries.activityCompose)
        implementation(platform(MainLibraries.composeBom))
        implementation(MainLibraries.ui)
        implementation(MainLibraries.uiGraphics)
        implementation(MainLibraries.uiToolingPreview)
        implementation(MainLibraries.material3)
        testImplementation(MainLibraries.junit)
        androidTestImplementation(MainLibraries.androidxTestExtJunit)
        androidTestImplementation(MainLibraries.espressoCore)
        androidTestImplementation(platform(MainLibraries.composeBom))
        androidTestImplementation(MainLibraries.uiTestJunit4)
        debugImplementation(MainLibraries.uiTooling)
        debugImplementation(MainLibraries.uiTestManifest)

        // Common
        implementation(CommonLibraries.navigationCompose)
        implementation(CommonLibraries.lifecycleViewModelCompose)
        implementation(CommonLibraries.lifecycleRuntimeCompose)


        // Dagger Hilt
        add("kapt", DependencyInjectionLibraries.googleDaggerHiltCompiler)
        implementation(DependencyInjectionLibraries.googleDaggerHilt)
        implementation(DependencyInjectionLibraries.hiltNavigationCompose)

        // Retrofit
        implementation(NetworkLibraries.retrofit)
        implementation(NetworkLibraries.retrofitGson)
        implementation(NetworkLibraries.okhttp3LoggingInterceptor)
    }
}