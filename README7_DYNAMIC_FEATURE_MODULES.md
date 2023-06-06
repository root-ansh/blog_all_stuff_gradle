# Dynamic feature modules
Google allows apps to reduce install time size by allowing apps to download whole modules at runtime. this feature is called dynamic feature modules.
more details : https://developer.android.com/guide/playcore/feature-delivery

## Rule 1
Each module's gradle should follow these rules:

1. : dynamic modules' gradle must have `id 'com.android.dynamic-feature'` instead of `id 'com.android.application'`
2. : dynamic modules' gradle must not define `targetsdk`, `vesrionCode` `versionName`, `compileOptions`, `kotlinOptions`
3. : `app/build.gradle` should define `android.dynamicFeature = [':dynamic_module_folder_name']`
4. : dynamic modules' gradle must have a dependency on app module, i.e `implementation project(":app")`
5. : all  modules' gradle should have a dependency on ` "com.google.android.play:core:1.10.3"`
   1. this can be reduced by just putting `api("..playcore")` instead of  `implementation("..playcore")` in app module due to point 4

these rules are there because app module needs to identify which modules will be loaded at runtime

## Rule 2 :

1. The manifest of every dynamic module should contain this block:
```xml
 <dist:module dist:instant="false" dist:title="@string/title_df_setting">

    <dist:delivery>
        <dist:on-demand /><!-- or <dist:install-time />-->
    </dist:delivery>
  
    <dist:fusing dist:include="true" />
  
</dist:module>
```
- Regarding this block :
  - fusing include = true/false means that the following module will be available/unavailable in the apk itself(i.e install time) for the apks for device below android 5.0(21)
  - delivery install-time/on-demand means that the following module will be available/unavailable in the apk itself(i.e install time) for the apks for device above android 5.0(21)
  - instant = false/true means this module is available as instant module or not. usually used by games for promotion

2.title must be a string resource in the main(app) module and **not directly a string** . also, the string should be marked as keep in proguard of the app module

3. The main module's manifest.xml will have an application tag with attribute `name:com.google.android.play.core.splitcompat.SplitCompatApplication` or `name:xyz` where xyz is a class extending splitcompatapplication


## Rules : Kotlin

1. Each df(irrespective of whether delivery dist = install time or runtime) as well as app module's activity should override attach base context like this:
   ```kotlin
    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        SplitCompat.installActivity(this)
    }
    ```
2. in app module:
   1. Application class should extend SplitCompatApplication : `class BaseSplitApp:SplitCompatApplication()`
   2. Activity of app class should call activity of df module class using this function:
   ```kotlin
       fun launchSettingsDF(activity: Activity){
            activity.startActivity(Intent().setClassName("work.curioustools.tdfm", "work.curioustools.df_setting.SettingsActivity"))
       }
   ```
   3. Installation of a module happens using this function:
    ```kotlin
       val myModules = listOf("df_setting")//"dfi_help"
        fun installModuleInForeground(ctx:AppCompatActivity,onComplete:(Task<Int?>)->Unit){
            val request = SplitInstallRequest.newBuilder().run {
                myModules.forEach { addModule(it) }
                build()
            }
    
            val listener = SplitInstallStateUpdatedListener {
                logState(it)
                if(it.status()==SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION){
                    SplitInstallManagerFactory.create(ctx).startConfirmationDialogForResult(it,ctx,100)
                }
            }
            SplitInstallManagerFactory
                .create(ctx).also {sim ->
                    sim.registerListener(listener)
                    sim.startInstall(request).addOnCompleteListener{
                        onComplete.invoke(it)
                        sim.unregisterListener(listener)
                    }
                }
    
        }
    ```
   
   4. Checking whether a module is available is done using this command :  `SplitInstallManagerFactory.create(ctx).installedModules.joinToString(",")`
   5. thus, the sequence of executing operations should be as follows:  step 4, then 3 then 2. for step 3, it should show a notification in foreground


## Rules : Testing 

 To test dynamic features, you must upload your app to play console . create a release bundle (using bundle release)
   