plugins {
    id( "com.android.application")
    id( "org.jetbrains.kotlin.android")
}

android.namespace = "work.curioustools.p1_new"
android.compileSdk = 33

android.defaultConfig.applicationId = "work.curioustools.p1_new"
android.defaultConfig.minSdk = 24
android.defaultConfig.targetSdk = 33
android.defaultConfig.testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
android.defaultConfig.versionCode= 1
android.defaultConfig.versionName ="1.0"
android.defaultConfig.vectorDrawables.useSupportLibrary = true

android.compileOptions.sourceCompatibility = JavaVersion.VERSION_11
android.compileOptions.targetCompatibility = JavaVersion.VERSION_11

android.kotlinOptions.jvmTarget = android.compileOptions.sourceCompatibility.toString()
android.kotlinOptions.freeCompilerArgs = listOf("-Xjvm-default=compatibility", "-opt-in=kotlin.RequiresOptIn")

android.buildTypes.named("debug") {
    isMinifyEnabled = false
    isShrinkResources = false
    applicationIdSuffix = ".debug"
}
android.buildTypes.named("release") {
    isMinifyEnabled = true
    isShrinkResources = true
}
android.buildTypes.all {
    isCrunchPngs = false
    proguardFiles(android.getDefaultProguardFile("proguard-android-optimize.txt"), "proguard.pro")
}


dependencies.implementation( "androidx.core:core-ktx:1.8.0")
dependencies.implementation( "androidx.appcompat:appcompat:1.5.1")
dependencies.implementation( "com.google.android.material:material:1.7.0")
dependencies.implementation ("androidx.constraintlayout:constraintlayout:2.1.4")
dependencies.testImplementation( "junit:junit:4.13.2")
dependencies.androidTestImplementation ("androidx.test.ext:junit:1.1.3")
dependencies.androidTestImplementation( "androidx.test.espresso:espresso-core:3.4.0")


/**

plugins {
id( "com.android.application")
id( "org.jetbrains.kotlin.android")
}

android {
namespace = "work.curioustools.p1_new"
compileSdk = 33

defaultConfig {
applicationId = "work.curioustools.p1_new"
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
//compileOptions= new ArrayList<String>("-Xjvm-default=compatibility", "-opt-in=kotlin.RequiresOptIn")
//freeCompilerArgs = listOf("-Xjvm-default=compatibility", "-opt-in=kotlin.RequiresOptIn")
}

}

dependencies {

implementation( "androidx.core:core-ktx:1.8.0")
implementation( "androidx.appcompat:appcompat:1.5.1")
implementation( "com.google.android.material:material:1.7.0")
implementation ("androidx.constraintlayout:constraintlayout:2.1.4")
testImplementation( "junit:junit:4.13.2")
androidTestImplementation ("androidx.test.ext:junit:1.1.3")
androidTestImplementation( "androidx.test.espresso:espresso-core:3.4.0")
}

*/