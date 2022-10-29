gradle.properties file

* this is a [.properties](https://en.wikipedia.org/wiki/.properties) file that is used by gradle build tool for configuring projects and plugins
* key-values defined here will be read by gradle and its plugins and will customise their execution behavior accordingly
* this file is mostly autoread when we initiate the build process, unlike local.properties or any other custom created properties file

> ***Note: Some common and useful properties are defined in the following [gradle.properties](link-todo)  file:***


```text
Note : properties is one of the many ways to provide customisation options to a gradle plugin that will modify its build/compilation process. others include:
 - providing options via gradle command
 - providing options via IDE settings
 - providing options via properties file
 - providing options via runtime gradle files
 
 About IDE Settings
 As IDE controls the building/execution of various android projects, it also provides certain mechanisms to gradle plugins to provide custom property support via IDE settings directly. such properties provided via IDE Settings will always override the same properties defined in `.properties` file
```

----------------------------------------------------------------------------------------------------------------------------------------------------------------

local.properties file

* this is another common file used for providing values that will customise the behavior of  overall project (and sometimes gradle plugins too) .
* unlike gradle.properties file, this is not autoread and loaded into the system. you will need to write custom code in order to read keys from here and have their values available at runtime in your java code.
* this is commonly used for providing api keys and other sensitive info to your app without adding them in a publically accessible vcs repo. local.properties should not be included in the vcs monitored files
* the properties defined in local.properties can be read using a general method of reading properties file : https://medium.com/@anshsachdevawork/securing-your-keys-with-gradle-variables-648b7faecf67
* alternatively, this plugin can be used for automatically reading gradle files: https://github.com/google/secrets-gradle-plugin
----------------------------------------------------------------------------------------------------------------------------------------------------------------

gradle-wrapper.properties
* this is an internal properties file that is used by AS to determine version of gradle to be used for compiling project. 
* it is also read automatically, but the info from keys is not accessible in runtime code. we usually don't add any other properties in here

----------------------------------------------------------------------------------------------------------------------------------------------------------------


