plugins {
    id(Plugins.androidApplication)
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.arya.danesh.myresume"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.arya.danesh.myresume"
        minSdk = 28 // 28 debug and 29 def
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        create("benchmark") {
            initWith(buildTypes.getByName("release"))
            signingConfig = signingConfigs.getByName("debug")
            matchingFallbacks += listOf("release")
            isDebuggable = false
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
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
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
    implementation(Dependencies.composeMaterial)
    implementation(Dependencies.constraintlayoutCompose)
    implementation(Dependencies.lottieCompose)
    implementation(Dependencies.supportAnnotations)
    implementation(Dependencies.coreSplashscreen)


    testImplementation(Dependencies.junitD)
    androidTestImplementation(Dependencies.extJunit)
    androidTestImplementation(Dependencies.espressoCore)
    androidTestImplementation(platform(Dependencies.composeBom))
    androidTestImplementation(Dependencies.uiTestJunit4)
    debugImplementation(Dependencies.uiTooling)
    debugImplementation(Dependencies.uiTestManifest)

    debugImplementation(Dependencies.leakcanaryAndroid)


    //My Libs
    implementation(project(Modules.coreUI))
    implementation(project(Modules.utilitiesModule))
    implementation(project(Modules.sandboxMessenger))

    implementation(Dependencies.hiltAndroid)
//    kapt(Dependencies.hiltCompiler)
    kapt(Dependencies.hiltAndroidCompiler)

    implementation(Dependencies.hiltNavigationCompose)
//    implementation(Dependencies.hiltLifecycleViewModel)


    implementation(Dependencies.retrofit)
    implementation(Dependencies.okhttp)
    implementation(Dependencies.moshi)
    implementation(Dependencies.moshiConverter)
    implementation(Dependencies.loggingInterceptor)

    implementation(Dependencies.kotlinxCoroutinesCore)
    implementation(Dependencies.kotlinxCoroutinesAndroid)

    implementation(Dependencies.coil)

    implementation(Dependencies.youtubeplayer)

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