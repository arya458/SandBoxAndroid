plugins {
    id(Plugins.androidLibrary)
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")

}

android {
    namespace = "com.sandbox.sandboxmessenger"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
}

dependencies {

    implementation(Dependencies.coreKTX)
    implementation(Dependencies.lifeCycle)
    implementation(Dependencies.activityCompose)
    implementation(platform(Dependencies.composeBom))
    implementation(Dependencies.composeUi)
    implementation(Dependencies.uiGraphics)
    implementation(Dependencies.uiTooling)
    implementation(Dependencies.composeMaterial3)
    implementation(Dependencies.navigationCompose)


    implementation(project(Modules.coreUI))
    implementation(project(Modules.utilitiesModule))


    testImplementation(Dependencies.junitD)
    androidTestImplementation(Dependencies.extJunit)
    androidTestImplementation(Dependencies.espressoCore)
    androidTestImplementation(platform(Dependencies.composeBom))
    androidTestImplementation(Dependencies.uiTestJunit4)
    debugImplementation(Dependencies.uiTooling)
    debugImplementation(Dependencies.uiTestManifest)
    implementation(Dependencies.hiltAndroid)
    kapt(Dependencies.hiltAndroidCompiler)
    implementation(Dependencies.hiltNavigationCompose)

    implementation(Dependencies.trixnity_clientserverapi)
    implementation(Dependencies.trixnity_client)
    implementation(Dependencies.trixnity_clientserverapi_model)
    implementation(Dependencies.trixnity_api_client)
    implementation(Dependencies.serialization_json)
    implementation(Dependencies.slf4j)
    implementation(Dependencies.logback_android)
    implementation(Dependencies.ktor)



}


// Allow references to generated code
kapt {
    correctErrorTypes = true
}