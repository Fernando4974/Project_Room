// Top-level build file where you can add configuration options common to all sub-projects/modules.

pluginManagement {
    repositories {
        // Debes asegurarte de que este repositorio esté aquí para descargar el plugin de Hilt.
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
}

fun pluginManagement(function: () -> Unit) {}
