package utils

import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.implementation(
    notation: String
) = add("implementation", notation)

fun DependencyHandler.implementation(
    dependencyNotation: Any
) = add("implementation", dependencyNotation)

fun DependencyHandler.testImplementation(
    notation: String
) = add("testImplementation", notation)

fun DependencyHandler.testImplementation(
    dependencyNotation: Any
) = add("testImplementation", dependencyNotation)

fun DependencyHandler.androidTestImplementation(
    notation: String
) = add("androidTestImplementation", notation)

fun DependencyHandler.androidTestImplementation(
    dependencyNotation: Any
) = add("androidTestImplementation", dependencyNotation)

fun DependencyHandler.debugImplementation(
    notation: String
) = add("debugImplementation", notation)

fun DependencyHandler.debugImplementation(
    dependencyNotation: Any
) = add("debugImplementation", dependencyNotation)
