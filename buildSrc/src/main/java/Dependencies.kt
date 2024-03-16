object ApplicationId{
    const val id = "com.pens.planduit"
}

object Versions{
    const val versionMajor = 1
    const val versionMedium = 0
    const val versionMinor = 0
    const val buildNum = 2

    val versionCode
        get() = 100000000 + (
            100 * Versions.versionMajor + 10 * Versions.versionMedium + Versions.versionMinor
            ) * 1000 + Versions.buildNum

    val versionName
        get() = "${Versions.versionMajor}.${Versions.versionMedium}.${Versions.versionMinor}"

    const val androidMinSdkVersion = 24
    const val androidTargetSdkVersion = 34
    const val androidCompileSdkVersion = 34

    const val coreKtx = "1.12.0"
    const val androidGradle = "8.2.2"
    const val kotlin = "1.9.0"
    const val daggerHilt = "2.49"
    const val junit = "4.13.2"
    const val androidxTestExtJunit = "1.1.5"
    const val espressoCore = "3.5.1"
    const val lifecycleRuntimeKtx = "2.7.0"
    const val activityCompose = "1.8.2"
    const val composeBom = "2024.02.01"
    const val androidxNavigation = "2.7.7"
    const val androidxLifecycle = "2.7.0"
    const val androidxHilt = "1.2.0"
    const val squareUpRetrofit = "2.9.0"
    const val squareUpOkhttp3 = "4.9.1"
    const val lottie = "6.0.0"
    const val gms = "4.4.1"
    const val firebase = "32.7.4"
}

object MainLibraries {
    const val androidCoreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val junit = "junit:junit:${Versions.junit}"
    const val androidxTestExtJunit = "androidx.test.ext:junit:${Versions.androidxTestExtJunit}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
    const val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleRuntimeKtx}"
    const val activityCompose = "androidx.activity:activity-compose:${Versions.activityCompose}"
    const val composeBom = "androidx.compose:compose-bom:${Versions.composeBom}"
    const val ui = "androidx.compose.ui:ui"
    const val uiGraphics = "androidx.compose.ui:ui-graphics"
    const val uiTooling = "androidx.compose.ui:ui-tooling"
    const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview"
    const val uiTestManifest = "androidx.compose.ui:ui-test-manifest"
    const val uiTestJunit4 = "androidx.compose.ui:ui-test-junit4"
    const val material3 = "androidx.compose.material3:material3"
    const val androidToolBuild = "com.android.tools.build:gradle:${Versions.androidGradle}"
    const val jetbrainsKotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
}

object CommonLibraries {
    const val navigationCompose = "androidx.navigation:navigation-compose:${Versions.androidxNavigation}"
    const val lifecycleViewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.androidxLifecycle}"
    const val lifecycleRuntimeCompose = "androidx.lifecycle:lifecycle-runtime-compose:${Versions.androidxLifecycle}"
    const val lottie = "com.airbnb.android:lottie-compose:${Versions.lottie}"
}

object DependencyInjectionLibraries {
    const val googleDaggerHilt = "com.google.dagger:hilt-android:${Versions.daggerHilt}"
    const val googleDaggerHiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.daggerHilt}"
    const val hiltNavigationCompose = "androidx.hilt:hilt-navigation-compose:${Versions.androidxHilt}"
}

object NetworkLibraries {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.squareUpRetrofit}"
    const val retrofitGson = "com.squareup.retrofit2:converter-gson:${Versions.squareUpRetrofit}"
    const val okhttp3LoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.squareUpOkhttp3}"
}

object FirebaseLibraries {
    const val firebaeBom = "com.google.firebase:firebase-bom:${Versions.firebase}"
    const val firebaseAnalytics = "com.google.firebase:firebase-analytics"
}

object Plugins {
    const val GoogleDaggerHilt = "com.google.dagger.hilt.android:${Versions.daggerHilt}"
}