import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.dagger.hilt)
    id("com.google.devtools.ksp")
    id("kotlin-parcelize")
}

val keystoreProperties = Properties()
val keystorePropertiesFile = rootProject.file("keystore/key.properties")
if (keystorePropertiesFile.exists()) {
    keystoreProperties.load(keystorePropertiesFile.inputStream())
}

val configProperties = Properties()
val configFile = rootProject.file("gradle/config/config.properties")
if (configFile.exists()) {
    configProperties.load(configFile.inputStream())
}

android {
    namespace = configProperties["applicationId"] as String
    compileSdk = (configProperties["targetSdk"] as String).toInt()

    composeOptions{
        kotlinCompilerExtensionVersion = "1.8.1"
    }

    defaultConfig {
        applicationId = configProperties["applicationId"] as String
        minSdk = (configProperties["minSdk"] as String).toInt()
        targetSdk = (configProperties["targetSdk"] as String).toInt()
        versionCode = (configProperties["versionCode"] as String).toInt()
        versionName = configProperties["versionCode"] as String
        multiDexEnabled = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        create("release"){
            keyAlias = keystoreProperties["keyAlias"] as String
            keyPassword = keystoreProperties["keyPassword"] as String
            storeFile = rootProject.file(keystoreProperties["storeFile"] as String)
            storePassword = keystoreProperties["storePassword"] as String
        }
    }
    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            isDebuggable = true
            applicationIdSuffix = ".debug"
            signingConfig = signingConfigs.getByName("debug")
        }
        getByName("release") {
            isMinifyEnabled = true
            isDebuggable = false
//            signingConfig = signingConfigs.getByName("release")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        create("staging") {
            initWith(getByName("debug"))
            manifestPlaceholders["hostName"] = "Stg MoviesApp"
            applicationIdSuffix = ".stg"
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }


    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    lint{
        disable += "NullSafeMutableLiveData"
    }
}

dependencies {
// Core AndroidX & Lifecycle
    implementation(libs.androidx.core.ktx)      // Kotlin extensions for core Android APIs
    implementation(libs.androidx.lifecycle.runtime.ktx)      // Lifecycle-aware components
    implementation(libs.androidx.core.splashscreen)      // Splash screen API

    // Jetpack Compose - BOM
    implementation(platform(libs.androidx.compose.bom))      // Compose Bill of Materials (BOM)

    // Jetpack Compose - Core UI
    implementation(libs.androidx.activity.compose)      // Activity support for Compose
    implementation(libs.androidx.ui)      // Core Compose UI elements
    implementation(libs.androidx.ui.graphics)      // Compose graphics primitives

    // Jetpack Compose - Material Design
    implementation(libs.androidx.material3)      // Material 3 UI components
    implementation(libs.androidx.compose.material)      // Material 2 components (legacy support)
    implementation(libs.androidx.icons.extended)      // Extended material icons

    // Jetpack Compose - Navigation & State
    implementation(libs.compose.navigation)      // Navigation support in Compose
    implementation(libs.compose.hilt.navigation)      // Hilt + Navigation integration
    implementation(libs.androidx.compose.livedata)      // LiveData support in Compose

    // Jetpack Compose - Tooling & Preview
    implementation(libs.androidx.ui.tooling.preview)      // Compose UI preview support
    debugImplementation(libs.androidx.ui.tooling)      // Compose UI tools (debug only)
    debugImplementation(libs.androidx.ui.test.manifest)      // Manifest for Compose UI tests (debug)

    // Navigation (Non-Compose)
    implementation(libs.androidx.navigation)      // AndroidX Navigation components

    // Room Database
    implementation(libs.androidx.room.runtime)      // Room runtime
    ksp(libs.androidx.room.compiler)      // Room annotation processor (KSP)
    annotationProcessor(libs.androidx.room.compiler)      // Annotation processor (for fallback/tests)

    // Hilt for Dependency Injection
    implementation(libs.hilt.android)      // Hilt core
    ksp(libs.hilt.compiler)      // Hilt code generation
    kspAndroidTest(libs.hilt.android.compiler)      // Hilt codegen for instrumentation tests

    // Networking (Retrofit + OkHttp)
    implementation(libs.squareup.retrofit)      // Retrofit for networking
    implementation(libs.squareup.retrofit.gson)      // Gson converter for Retrofit
    implementation(libs.squareup.okhttp3.logging)      // OkHttp logging interceptor

    // Testing - Unit Tests
    testImplementation(libs.junit)      // JUnit for unit testing

    // Testing - Android Instrumentation Tests
    androidTestImplementation(libs.androidx.junit)      // AndroidX JUnit extensions
    androidTestImplementation(libs.androidx.espresso.core)      // Espresso UI test framework
    androidTestImplementation(platform(libs.androidx.compose.bom))      // Compose BOM for tests
    androidTestImplementation(libs.androidx.ui.test.junit4)


}