plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.example.chatapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.chatapplication"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    packaging {
        // Exclude the META-INF/INDEX.LIST files from being merged
        resources.excludes.add("META-INF/INDEX.LIST")
        resources.excludes.add("META-INF/DEPENDENCIES")
        resources.excludes.add("org.jetbrains.kotlin:kotlin-stdlib:1.8.22")
        resources.excludes.add("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.6.21")
    }

}


dependencies {

    implementation(libs.appcompat)
    implementation(libs.activity)
    implementation ("com.google.android.material:material:1.8.0")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation(libs.monitor)
    implementation(libs.androidx.junit)
    testImplementation("junit:junit:4.12")
    androidTestImplementation("org.testng:testng:6.9.6")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.12.0")
    implementation ("com.google.firebase:firebase-firestore:24.4.4")
    implementation ("com.google.firebase:firebase-storage:20.1.0")
    implementation ("com.google.firebase:firebase-messaging:23.1.2")
    implementation ("com.hbb20:ccp:2.5.0")
    implementation ("com.google.firebase:firebase-bom:31.3.0")
    implementation ("com.google.firebase:firebase-auth:21.0.1")
    implementation ("com.firebaseui:firebase-ui-firestore:8.0.2")
    implementation ("com.github.dhaval2404:imagepicker:2.1")
    implementation ("com.github.bumptech.glide:glide:4.15.1")
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    implementation("com.google.auth:google-auth-library-oauth2-http:1.23.0")
    implementation ("org.jetbrains.kotlin:kotlin-stdlib:1.8.22")
    implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.8.22")
    implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.22")


}