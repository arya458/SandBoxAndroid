object Dependencies {


    //    implementation("androidx.core:core-ktx:1.12.0")
    val coreKTX by lazy { "androidx.core:core-ktx:${Versions.coreKTX}" }

    //    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    val lifeCycle by lazy { "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifeCycle}" }

    //    implementation("androidx.activity:activity-compose:1.8.0")
    val activityCompose by lazy { "androidx.activity:activity-compose:${Versions.activityCompose}" }

    //    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    val composeBom by lazy { "androidx.compose:compose-bom:${Versions.composeBom}" }

    //    implementation("androidx.appcompat:appcompat:1.6.1")
    val appCompact by lazy { "androidx.appcompat:appcompat:${Versions.appCompact}" }

    //    implementation("androidx.compose.ui:ui")
    val composeUi by lazy { "androidx.compose.ui:ui" }

    //    implementation("androidx.compose.ui:ui-graphics")
    val uiGraphics by lazy { "androidx.compose.ui:ui-graphics" }

    //    implementation("androidx.compose.ui:ui-tooling-preview")
    val uiToolingPreview by lazy { "androidx.compose.ui:ui-tooling-preview" }

    //    implementation("androidx.compose.material3:material3")
    val composeMaterial3 by lazy { "androidx.compose.material3:material3" }

    //    implementation("androidx.navigation:navigation-compose:2.7.5")
    val navigationCompose by lazy { "androidx.navigation:navigation-compose:${Versions.navigationCompose}" }

    //    implementation("androidx.compose.material:material:1.5.4")
    val composeMaterial by lazy { "androidx.compose.material:material:${Versions.composeMaterial}" }

    //    implementation("androidx.constraintlayout:constraintlayout-compose:1.1.0-alpha13")
    val constraintlayoutCompose by lazy { "androidx.constraintlayout:constraintlayout-compose:${Versions.constraintlayoutCompose}" }

    //    implementation("com.airbnb.android:lottie-compose:6.2.0")
    val lottieCompose by lazy { "com.airbnb.android:lottie-compose:${Versions.lottieCompose}" }

    //    implementation("com.android.support:support-annotations:28.0.0")
    val supportAnnotations by lazy { "com.android.support:support-annotations:${Versions.supportAnnotations}" }

    //    implementation("androidx.core:core-splashscreen:1.0.0")
    val coreSplashscreen by lazy { "androidx.core:core-splashscreen:${Versions.coreSplashscreen}" }

    //
//    testImplementation("junit:junit:4.13.2")
    val junitD by lazy { "junit:junit:${Versions.junitD}" }

    //    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    val extJunit by lazy { "androidx.test.ext:junit:${Versions.extJunit}" }

    //    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    val espressoCore by lazy { "androidx.test.espresso:espresso-core:${Versions.espressoCore}" }

    //    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
//    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    val uiTestJunit4 by lazy { "androidx.compose.ui:ui-test-junit4" }

    //    debugImplementation("androidx.compose.ui:ui-tooling")
    val uiTooling by lazy { "androidx.compose.ui:ui-tooling" }

    //    debugImplementation("androidx.compose.ui:ui-test-manifest")
    val uiTestManifest by lazy { "androidx.compose.ui:ui-tooling" }

    //
//    debugImplementation("com.squareup.leakcanary:leakcanary-android:3.0-alpha-1")
    val leakcanaryAndroid by lazy { "com.squareup.leakcanary:leakcanary-android:${Versions.leakcanaryAndroid}" }

    //
//    implementation("com.google.dagger:hilt-android:2.44")
    val hiltAndroid by lazy { "com.google.dagger:hilt-android:${Versions.hiltAndroid}" }

    //    implementation("com.google.dagger:hilt-compiler")
    val hiltCompiler by lazy { "com.google.dagger:hilt-compiler:${Versions.hiltCompiler}" }

    //    kapt("com.google.dagger:hilt-android-compiler:2.44")
    val hiltAndroidCompiler by lazy { "com.google.dagger:hilt-android-compiler:${Versions.hiltAndroidCompiler}" }

    val hiltNavigationCompose by lazy { "androidx.hilt:hilt-navigation-compose:${Versions.hiltNavigationCompose}" }

    val hiltLifecycleViewModel by lazy { "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hiltLifecycleViewModel}" }

//    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    val retrofit by lazy { "com.squareup.retrofit2:retrofit:${Versions.retrofit}" }


    //implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.12")
    val okhttp by lazy { "com.squareup.okhttp3:okhttp:${Versions.okhttp}" }

    //implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    val gsonConverter by lazy { "com.squareup.retrofit2:converter-gson:${Versions.gsonConverter}" }

    //runtimeOnly("com.squareup.moshi:moshi-kotlin:1.15.1")
    val moshi by lazy { "com.squareup.moshi:moshi-kotlin:${Versions.moshi}" }

    //runtimeOnly("com.squareup.retrofit2:converter-moshi:2.9.0")
    val moshiConverter by lazy { "com.squareup.retrofit2:converter-moshi:${Versions.moshiConverter}" }

    //implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.12")
    val loggingInterceptor by lazy { "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}" }

    //implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
    val kotlinxCoroutinesCore by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinxCoroutinesCore}" }

    //runtimeOnly("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.0")
    val kotlinxCoroutinesAndroid by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinxCoroutinesAndroid}" }

    //implementation("io.coil-kt:coil:2.6.0")
    val coil by lazy { "io.coil-kt:coil-compose:${Versions.coil}" }

    //implementation("androidx.window:window:1.2.0")
    val androidxWindow by lazy { "androidx.window:window:${Versions.androidxWindow}" }

    //"implementation" ("com.pierfrancescosoffritti.androidyoutubeplayer:core:11.1.0")
    val youtubeplayer by lazy { "com.pierfrancescosoffritti.androidyoutubeplayer:core:${Versions.youtubeplayer}" }

    //    implementation("net.folivo:trixnity-clientserverapi-client:${trixnityVersion}")
    val trixnity_clientserverapi by lazy { "net.folivo:trixnity-clientserverapi-client:${Versions.trixnity}" }

    //    implementation("net.folivo:trixnity-client:${trixnityVersion}")
    val trixnity_client by lazy { "net.folivo:trixnity-client:${Versions.trixnity}" }

    //    implementation("net.folivo:trixnity-clientserverapi-model:${trixnityVersion}")
    val trixnity_clientserverapi_model by lazy { "net.folivo:trixnity-clientserverapi-model:${Versions.trixnity}" }

    //    implementation("net.folivo:trixnity-api-client:${trixnityVersion}")
    val trixnity_api_client by lazy { "net.folivo:trixnity-api-client:${Versions.trixnity}" }

    //    implementation ("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.2")
    val serialization_json by lazy { "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.serialization_json}" }

    //    implementation("org.slf4j:slf4j-api:2.0.9") // logging in trixnity-messenger
    val slf4j by lazy { "org.slf4j:slf4j-api:${Versions.slf4j}" }

    //    implementation("com.github.tony19:logback-android:3.0.0") // logging in trixnity-messenger
    val logback_android by lazy { "com.github.tony19:logback-android:${Versions.logback_android}" }

    //    implementation("io.ktor:ktor-client-android:2.3.5") // HTTP client used in trixnity-messenger
    val ktor by lazy { "io.ktor:ktor-client-android:${Versions.ktor}" }

    //    implementation("androidx.room:room-runtime:2.5.2")
    val room_runtime by lazy { "androidx.room:room-runtime:${Versions.room_version}" }

    //    implementation "androidx.room:room-ktx:2.5.2"
    val room_ktx by lazy { "androidx.room:room-ktx:${Versions.room_version}" }

    //    annotationProcessor("androidx.room:room-compiler:2.5.2")
    val room_compiler by lazy { "androidx.room:room-compiler:${Versions.room_version}" }

    // implementation 'org.jetbrains:annotations-java5:15.0'
    val annotations by lazy { "org.jetbrains:annotations-java5:${Versions.annotations_version}" }

    // implementation 'com.google.code.gson:gson:2.8.9'
    val gson by lazy { "com.google.code.gson:gson:${Versions.gson}" }

    //    implementation 'com.github.hcaptcha:hcaptcha-android-sdk:x.y.z'
    val hcaptcha by lazy { "com.github.hcaptcha:hcaptcha-android-sdk:${Versions.hcaptcha}" }

    //implementation("com.google.android.gms:play-services-safetynet:18.0.1")
    val safetynet by lazy { "com.google.android.gms:play-services-safetynet:${Versions.safetynet}" }



}

object Modules {

    const val utilitiesModule = ":utilities"
    const val sandboxMessenger = ":sandboxMessenger"
    const val coreUI = ":coreUI"

}

object Plugins {

    const val androidApplication = "com.android.application"
    const val androidLibrary = "com.android.library"
}