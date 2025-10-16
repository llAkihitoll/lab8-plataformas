plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    //alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt)
    id("org.jetbrains.kotlin.kapt")
}
//hilt {
  //  enableAggregatingTask = false
//}
android {
    namespace = "com.example.lab8"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.lab8"
        minSdk = 24
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
// --- Core y Lifecycle de AndroidX ---
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation("androidx.compose.runtime:runtime-livedata:1.6.7") // Usa una versión actualizada y explícita si no está en el catálogo

    // --- Compose UI (BOM gestiona las versiones) ---
    // Declara el BOM UNA SOLA VEZ
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3) // Solo esta línea para Material3
    implementation("androidx.compose.material:material-icons-extended:1.6.7") // Usa una versión actualizada

    // --- Inyección de Dependencias (Hilt) - ¡CORREGIDO! ---
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0") // Dependencia para navegación con Compose
    // ELIMINADAS: Todas las declaraciones duplicadas y obsoletas de Hilt

    // --- Base de Datos (Room) ---
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-paging:2.6.1")
    // --- Red (Retrofit) ---
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // --- Paginación (Paging 3) ---
    implementation(libs.androidx.paging.compose) // Usa la versión del catálogo, que debería ser estable

    // --- Carga de Imágenes (Coil) ---
    implementation("io.coil-kt:coil-compose:2.6.0") // Versión actualizada

    // --- Dependencias de Test ---
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom)) // BOM también para los tests
    androidTestImplementation(libs.androidx.ui.test.junit4)

    // --- Dependencias de Debug ---
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)



/*
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.paging.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    implementation(libs.hilt.lifecycle.viewmodel)
    kapt(libs.hilt.lifecycle.compiler)

    //implementation("com.google.dagger:hilt-android:2.47")
    //kapt("com.google.dagger:hilt-compiler:2.47")
    implementation("androidx.room:room-runtime:2.6.0")
    implementation("androidx.room:room-ktx:2.6.0")
    kapt("androidx.room:room-compiler:2.6.0")
    implementation("androidx.room:room-paging:2.6.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")
    implementation("androidx.compose.runtime:runtime-livedata:1.5.2")
    implementation("androidx.paging:paging-compose:1.0.0-alpha20")
    implementation("androidx.compose.material3:material3:1.1.1")
    implementation("androidx.compose.material:material-icons-extended:1.5.0")
    implementation("com.squareup:javapoet:1.13.0")
    implementation("io.coil-kt:coil-compose:2.4.0")
    implementation(platform(libs.androidx.compose.bom))
*/
}

