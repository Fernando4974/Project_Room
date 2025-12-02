import org.gradle.kotlin.dsl.invoke

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    // KSP: Procesador de anotaciones moderno (para Room y Hilt)
    alias(libs.plugins.ksp)
    // Plugin de Hilt para integrar DI con Android
    alias(libs.plugins.hilt.android)
}

android {
    namespace = "com.example.proyect_room_fernando"
    // Usando una variable o función 'release(36)' no es estándar.
    // Lo he corregido asumiendo que quieres el SDK 36.
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.proyect_room_fernando"
        minSdk = 29
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        // Mejor usar el valor de JavaVersion.VERSION_11 directamente
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        // CORRECCIÓN CLAVE: Usar la sintaxis apropiada para obtener un String del catálogo.
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }
}
dependencies {
    // --- Compose Navigation ---
    implementation (libs.navigation.compose)

    // --- Lifecycle / ViewModel ---
    implementation (libs.lifecycle.viewmodel.ktx)
    implementation (libs.lifecycle.viewmodel.compose)

    // --- Coroutines (operaciones en background / Room) ---
    implementation (libs.coroutines.core)
    implementation (libs.coroutines.android)

    // --- Room (base de datos local) ---
    // Usamos room.ktx que incluye runtime y soporte para coroutines
    implementation (libs.room.ktx)
    implementation(libs.androidx.scenecore)
    // KSP para el procesador de anotaciones de Room (IMPORTANTE: usamos KSP, no kapt)
    ksp(libs.room.compiler)

    // --- Hilt (inyección de dependencias) ---
    implementation (libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation (libs.hilt.navigation.compose)

// ------------------- DEPENDENCIAS ESTÁNDAR DE ANDROID/COMPOSE -------------------
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    // Plataforma Compose BOM para gestionar versiones de Compose
    implementation(platform(libs.androidx.compose.bom))

    // UI y Material
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.material3)

    // Herramientas de desarrollo
    implementation(libs.androidx.compose.ui.tooling.preview)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    // --- Pruebas (Tests) ---
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
}