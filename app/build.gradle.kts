plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")

    //Add the Google services Gradle plugin
    id("com.google.gms.google-services")
}

android {
    namespace = "projet.mobile.kotlin.fitsv"
    compileSdk = 34

    defaultConfig {
        applicationId = "projet.mobile.kotlin.fitsv"
        minSdk = 26
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
            isMinifyEnabled = false
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
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {


    implementation("com.google.firebase:firebase-messaging-ktx:23.3.1")
    implementation("com.google.firebase:firebase-messaging:23.3.1")
    val kotlinVersion = "1.12.0"
    val composeVersion = "1.5.4"
    val activityComposeVersion = "1.8.0"
    val hiltVersion="2.48"
    val navComposeVersion = "1.0.0"
    val roomVersion = "2.5.1"
    val workVersion = "2.7.1"
    val hiltWorkVersion = "1.0.0"
    val retrofitVersion = "2.9.0"

    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:32.6.0")) //Import the Firebase BoM
    implementation("com.google.android.gms:play-services-auth:20.5.0") // Google sign in SDK
    // Firebase SDK
    implementation(platform("com.google.firebase:firebase-bom:32.0.0"))
    implementation("com.google.firebase:firebase-database-ktx")
    implementation("com.google.firebase:firebase-storage-ktx")
    implementation("com.google.firebase:firebase-auth-ktx")
    //Firebase UI Library
    implementation("com.firebaseui:firebase-ui-auth:8.0.2")
    implementation("com.firebaseui:firebase-ui-database:8.0.2")
    //Firebase In-App Messaging
    implementation("com.google.firebase:firebase-inappmessaging-display")
    implementation("com.google.firebase:firebase-analytics")
    // AppCompat
    implementation("androidx.appcompat:appcompat:1.6.1")

    implementation("androidx.wear.compose:compose-material:1.2.1")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.11")
    implementation("androidx.compose.material:material-icons-extended:1.5.4")
    implementation("com.google.accompanist:accompanist-permissions:0.33.2-alpha")


    // Room implementation with KSP
    implementation("androidx.room:room-runtime:$roomVersion")
    annotationProcessor("androidx.room:room-compiler:$roomVersion")
    ksp("androidx.room:room-compiler:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")

    // Hilt
    implementation("androidx.hilt:hilt-navigation-compose:$navComposeVersion")
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    ksp("com.google.dagger:hilt-android-compiler:$hiltVersion")

    // Worker
    implementation("androidx.work:work-runtime-ktx:$workVersion")
    implementation("androidx.hilt:hilt-work:$hiltWorkVersion")
    ksp("androidx.hilt:hilt-compiler:$hiltWorkVersion")


    // Retrofit (include okhttp)
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")

    implementation("androidx.core:core-ktx:$kotlinVersion")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:$activityComposeVersion")
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling-preview:$composeVersion")
    implementation("androidx.compose.material:material:$composeVersion")

    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.navigation:navigation-compose:2.7.5")
    implementation("androidx.compose.material3:material3-window-size-class:1.1.2")
    implementation("androidx.window:window:1.1.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$composeVersion")
    debugImplementation("androidx.compose.ui:ui-tooling:$composeVersion")
    debugImplementation("androidx.compose.ui:ui-test-manifest:$composeVersion")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:$composeVersion")

    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
}