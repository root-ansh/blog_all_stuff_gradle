# Gradle Architectures

<details open>

<summary><h3> About </h3></summary>

Gradle Architecture is well, not a term. It is not a single concept but rather a collection of approaches towards achieving some kind of Android app's code structure, which would make sense. An Android App may take 10-15 MB of your mobile's disk memory when installed, but its code needed to generate that build may span to 100,000+ files . Having these many files in a single folder would make navigation difficult and chances of shipping buggy code almost inevitable.

Fortunately Gradle's build system is quite smart in building Android codebase and therefore allows us to modularise our code as per our need. It does define certain rules to prevent cyclic dependencies and other issues, but if followed correctly, makes the codebase a delight to use.

</details>


<details >
<summary><h2> Approach 1 : 1 parent 1 child </h2></summary>

This is one of the most common architectures in Android environment . In this the gradle has 3 configurations files : `settings.gradle`, `/build.gradle` and `app/build.gradle`.  

- gradle will start the initialisation process from `settings.gradle` and define all the modules of the project. here it will find this line `include ':app'` and identify `app` folder as a module of the project.
- then it will read root's `/build.gradle` and initialise certain root level plugins(?need more research)
- finally it will read `app/build.gradle`, apply various plugins(?) and execute the `android` configuration(?) and include the dependencies with the build

**You can check the 3 gradle files from  [p1_basic](/p1_basic/) for the usual configuration.**

notice how the format follows:
```groovy
//settings.gradle
rootProject.name = "p1_basic"
include ':app'

// (root)/build.gradle
buildscript {
    repositories {/*google();mavenCentral();gradlePluginPortal(),...*/}
    dependencies { /* classpath "com.android.tools.build:gradle:7.3.1"; classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.20",... */}
}
allprojects {
    repositories {/*google();mavenCentral();gradlePluginPortal(),...*/}
}

//(root)/app/build.gradle
plugins {/*...*/}
android {/*...*/}
dependencies {/*...*/}
```

From these 3 files, we can get the following info:  

1. from `settings.gradle`, we define that project's root name will be `p1_basic` and it consist of only 1 module i.e, `app
2. for root's `build.gradle`, we dissect the various blocks:
   1. `buildscript` blocks runs the first for every gradle command. this block defines the libraries/plugins (aka `classpath`) that would be needed in one/multiple module and are therefore initialised before the initialisation process of each module. the repository block defines the online hosting servers from where the dependencies are supposed to be downloaded
   2. `allprojects` runs immediately after buildscript .here we can run code that is supposed to run before each module. currently it defines the repositories from where the various dependencies could  be extracted
3. app's build.gradle contains the initialisation code for app module. it defines various module flavours  options and configurations , followed by the dependencies

### Alternate (Modern Flavor): Ditch the `classpath`s (Also, use kotlin gradle files!)

new versions of Android Studio generated the similar 1 parent 1 child architecture app, but with slightly different content in gradle files. here, the root's build.gradle is kept clean and instead the initialisation info like the various server paths for downloading is moved to settings.gradle

**You can check the 3 gradle files from  [p1_new](/p1_new/) for the newer configuration.**

<u><i>Note 1: Gradle not only  understands groovy but kotlin too!</i></u>.  
you can simply rename your `build.gradle` file to `build.gradle.kts` , use `"` instead of `'` for strings and make some other small modifications, and you can have a perfect android project using 0 `.gradle` files!. 
**the [p1_new](/p1_new/) project has all the gradle files in kotlin**

<u><i>Note2: Where there's kotlin, there's the Higher order functions!</i></u>.  
The groovy language also uses [groovy scopes](https://www.baeldung.com/groovy/variable-scope) to declare the various properties. in kotlin, this is similar to using a higher order function like `with(x){..}` or `x.run{...}` where all the properties and methods of `x` will be accessible inside the block like `this.abc()`, where `this` is also optional.
I find it cool because i can instead write all the code as direct api lines instead of writingthe blocks which i find more understandable. **checkout the [app's gradle file](/p1_new/app/build.gradle) which is written in a similar manner**

</details>

<details >
<summary><h2> Approach 1 :  parent is everything </h2></summary>
</details>
