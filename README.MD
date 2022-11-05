# Gradle

*Gradle is where the magic happens!* And this repo is where we decode the magic :D


<details open>

<summary><h2>What is Gradle and why it is needed?</h2></summary>
This [answer](https://stackoverflow.com/a/24828666/7500651) (and other answers on this question) explain the gradle perfectly. It is simply an advanced build tool for compiling java software. more features can be seen [here](https://medium.com/@shivam.gosavi340_58315/gradle-101-introduction-1d83c0394942)

</details>




<details open>

<summary><h2> Gradle and android</h2></summary>


The relationship between android and gradle is described [here](https://developer.android.com/studio/build). in a summary:
- Android Studio uses Gradle as a tool to compile java/kotlin classes, their dependencies and other android assets into an executable APK of various flavors and configuration

- [AGP](https://developer.android.com/studio/build/extend-agp) is well, a plugin for gradle, just like `checkstyle` or `detekt plugin` . The nice thing about gradle is that it allows companies to create custom scripts or plugins that could be would be executed during gradle's build process life cycle. And with AGP, Google does exactly that : **adding new steps like compiling many different types of sources and linking them together into an APK (that can be run on a physical device)**, into gradle's standard build tasks



</details>


<details open>

<summary><h2>How the various gradle files work together?</h2></summary>

Let us pick the various gradle files present in our [p1_basic](p1_basic) project and try to pickup on them one by one:

</details>


<details open>

<summary><h3>1. `gradle-wrapper.properties` , `gradle-wrapper.jar`, `gradlew` and `gradlew.bat`</h3></summary>

These files are all connected and contribute to the start of code compilation process. Assuming that no command has been executed automatically by the AS, when we opened a project in AS and press run:
1. AS instructs the OS to execute `gradlew` or `gradlew.bat` executable files(depending upon the OS) with a command like `gradlew assembleDebug`
2. These files have commands which will check if the gradle version mentioned in the `gradle-wrapper.properties` exists on the OS (in cache or other areas accessible to AS, like `PATH`) , and if not will download it.
3. After downloading, the `gradlew` will start the `gradle-wrapper.jar` which will further execute the `build.gradle` files present in our project(and the originally downloaded gradle software, from step 2) to compile the complete app into an executable `.AAR` file
   - more info on [gradle-wrapper.jar ](https://docs.gradle.org/current/userguide/gradle_wrapper.html)

***Thus, to summarise,** the relation between `build.gradle files`, `gradle software` and `gradle-wrapper.jar` becomes similar to that of java files, JDK and Terminal : Terminal is where the commands like `javac file.java && java file` are written & java files contains the actual instructions needed to be executed and java files + jdk works together to generate an executable file that gets executed by processor*


</details>

----------------------------------------------------------------------------------------------------

<details open>

<summary><h3>2. `gradle.properties file`</h3></summary>

> update: before reading this section and next(local properties) section, you might also want to read [this article](https://medium.com/@shivam.gosavi340_58315/gradle-102-gradle-basics-798db70a6c20) which focuses on actual terminologies and how it is used for project/system initialisation.  personally, i like to see code first, understand it, then understand the meaning and then rewrite-everything to make it a summary, but that's just me. I will be updating this article with the info from above article in sometime later

- this is a [.properties](https://en.wikipedia.org/wiki/.properties) file that is used by gradle build tool for configuring projects and plugins(i.e., during step 3 )
- key-values defined here will be read by gradle and its plugins and will customise their execution behavior accordingly
- this file is usually auto-read by the associated plugins when we initiate the build process, unlike local.properties or any other custom created properties file

**Note: Some common and useful properties are defined in the following [gradle.properties](p1_basic/gradle.properties) file. These can vastly improve your gradle speed and performance, so you definitely want to check this .**


> Note2 : `.properties` file is one of the many ways to provide customisation options to a gradle plugin that will modify its build/compilation process. others include:
> - providing options via gradle command (like `gradlew assembleDebug --verbose`)
> - providing options via IDE settings
> - providing options via properties file
> - providing options via runtime `.gradle` files (i.e, the command blocks, like `android{...}` )
>
> About IDE Settings
> As IDE controls the building/execution of various android projects, it also provides certain mechanisms to gradle plugins to provide custom property support via IDE settings directly. such properties provided via IDE Settings will always override the same properties defined in `.properties` file

</details>


----------------------------------------------------------------------------------------------------

<details>

<summary><h3>3. `local.properties file`</h3></summary>

- this is another common file used for providing values that will customise the behavior of overall project (and sometimes gradle plugins too) .
- unlike gradle.properties file, this is not auto-read and loaded into the system. you will need to write custom code in order to read keys from here and have their values available at runtime in your java code.
- this is commonly used for providing api keys and other sensitive info to your app without adding them in a publicly accessible vcs repo. local.properties should not be included in the vcs monitored files
- the properties defined in local.properties can be read using a general method of reading properties file : https://medium.com/@anshsachdevawork/securing-your-keys-with-gradle-variables-648b7faecf67
- alternatively, this plugin can be used for automatically reading gradle files: https://github.com/google/secrets-gradle-plugin
- Interestingly, gradle does not recommend adding configurations for gradle plugins in this file, however AS automatically rewrites a few properties inside it if not available, [like these one](https://developer.android.com/studio/build#properties-files)


</details>


----------------------------------------------------------------------------------------------------


<details>

<summary><h3>4. `build.gradle` files in project and module and `settings.gradle`</h3></summary>

- These are the final gradle scripts that gets executed after the build environment has been set up.
- The project level gradle usually consists of the configuration required for the complete project and various plugin , while the module level gradle consists of configuration required for a particular module
- These configurations are of many formats: we are basically passing strings, integers, booleans, dependency paths , function-blocks, etc. to gradle to use them during execution

</details>


The common types of configurations are provided in the [next section](README2_GRADLE_ARCHS.md).

----

misc:

1. [x] what is gradle, why it is needed and how it works in general / with android?
2. [ ] plugin creation, understanding the execution process and much more: https://www.youtube.com/watch?v=YZE99qO7OtY
3. [ ] classpath vs plugins
4. [ ] single build.gradle vs android+root build.gradle vs multi module
5. [ ] `buildSrc` and support for kotlin
6. [ ] version catalogs
7. [ ] refresh versions
8. [ ] various android/gradle plugin blocks
9. [ ] gradle project level plugins vs module level plugins vs dependencies




