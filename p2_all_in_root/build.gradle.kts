plugins {
    id("com.android.application") version ("7.3.1")
    kotlin("android") version ("1.7.10")
    kotlin("plugin.serialization") version ("1.7.10")
    id("com.google.devtools.ksp") version ("1.7.10-1.0.6")
}

android {
    namespace = "work.curioustools.p2_all_in_root"
    compileSdk = 33
    defaultConfig {
        applicationId = "work.curioustools.p2_all_in_root"
        minSdk = 21
        targetSdk = 33
        versionCode= 1
        versionName ="1.0"
        vectorDrawables.useSupportLibrary = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        named("debug") {
            isMinifyEnabled = false
            isShrinkResources = false
            applicationIdSuffix = ".debug"
        }
        named("release") {
            isMinifyEnabled = true
            isShrinkResources = true
        }
        all {
            isCrunchPngs = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        viewBinding = true
    }


    kotlinOptions {
        jvmTarget = compileOptions.sourceCompatibility.toString()
    }
}

dependencies {
    implementation( "androidx.core:core-ktx:1.8.0")
    implementation( "androidx.appcompat:appcompat:1.5.1")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation( "com.google.android.material:material:1.7.0")
    implementation( "androidx.navigation:navigation-fragment-ktx:2.5.3")
    implementation( "androidx.navigation:navigation-ui-ktx:2.5.3")

    testImplementation( "junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.3")
    androidTestImplementation( "androidx.test.espresso:espresso-core:3.4.0")
}
