
plugins {
    id( "com.android.application")
    id( "org.jetbrains.kotlin.android")
}

android {
    namespace = "work.curioustools.rv_basic"
    compileSdk = 33

    defaultConfig {
        applicationId = "work.curioustools.rv_basic"
        minSdk = 24
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

    kotlinOptions {
        jvmTarget = compileOptions.sourceCompatibility.toString()
    }

}

dependencies {

    implementation( AndroidX.core.ktx)
    implementation( AndroidX.appCompat)
    implementation( Google.android.material)
    implementation (AndroidX.constraintLayout)
    testImplementation( Testing.junit4)
    androidTestImplementation (AndroidX.test.ext.junit)
    androidTestImplementation( AndroidX.test.espresso.core)
    implementation ("com.applandeo:material-calendar-view:_")

}
