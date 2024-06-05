//Android specific build.gradle.ktx

plugins {
    id("com.android.application")
    kotlin("android")

}

// https://maven.google.com/web/index.html?q=compiler#androidx.compose.compiler:compiler
val compose_compiler_version = "1.5.3"
// https://maven.google.com/web/index.html?q=ui#androidx.compose.ui:ui
val compose_ui_version = "1.6.5"

dependencies {
    val voyagerVersion = "1.1.0-beta02"
    implementation(project(":shared"))

    implementation("androidx.compose.compiler:compiler:$compose_compiler_version")
    implementation("androidx.compose.material:material:$compose_ui_version")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
    implementation("androidx.compose.ui:ui:$compose_ui_version")
    implementation("androidx.compose.ui:ui-tooling:$compose_ui_version")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation("androidx.compose.material3:material3-android:1.2.1")
    implementation("cafe.adriel.voyager:voyager-navigator:$voyagerVersion")

    //TODO: THOUGHT - I wonder if I can do the below for the androidx libraries so that I can create Composables in commonMain??
    api("io.realm.kotlin:library-base:1.11.0") //https://stackoverflow.com/questions/78163170/adding-realm-kotlin-as-a-dependency-in-an-android-library
}

android {
    compileSdk = 34
    defaultConfig {
        applicationId = "io.realm.kotlin.demo"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
        }
    }

    // Required by Compose
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = compose_compiler_version
    }
}
