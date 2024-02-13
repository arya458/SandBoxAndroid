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
    val daggerHilt by lazy { "com.google.dagger:hilt-android:${Versions.daggerHilt}" }

    //    implementation("com.google.dagger:hilt-compiler")
    val daggerHiltCompiler by lazy { "com.google.dagger:hilt-compiler" }

    //    kapt("com.google.dagger:hilt-android-compiler:2.44")
    val daggerHiltCompilerKapt by lazy { "com.google.dagger:hilt-android-compiler:${Versions.daggerHiltCompilerKapt}" }

}

object Modules{

    const val utilitiesModule = ":utilities"

}