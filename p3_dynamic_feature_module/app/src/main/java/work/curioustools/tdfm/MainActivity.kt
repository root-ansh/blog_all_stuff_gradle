package work.curioustools.tdfm

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.play.core.splitcompat.SplitCompat
import work.curioustools.tdfm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        SplitCompat.installActivity(this)

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val act =this
        var x = -1
        with(binding){
            logg("Installed modules: "+Splitwise.getInstalledModules(act))

            btIFG.setOnClickListener {
                logg("pressed btIFG")
                Splitwise.installModuleInForeground(act){
                    logg("pressed btIFG:$it")

                    if(it.isComplete) {
                        x= it.result
                        logg("${it.result}")
                    }
                }
            }

            btIBG.setOnClickListener {
                logg("pressed btIBG")

                Splitwise.installModuleInBackground(act){
                    logg("pressed btIBG. result : $it")

                    if(it.isComplete) logg("${it.result}")
                }
            }
            btCancel.setOnClickListener {
                logg("pressed btCancel")
                Splitwise.cancelDownloadingAModule(act,x)
            }
            btChecksession.setOnClickListener {
                logg("pressed btChecksession")

                Splitwise.isDownloadingAModule(act){
                    logg("isDownloadingAModule: callback")
                    Splitwise.logState(it)
                }
            }

            btLaunchDFIHelp.setOnClickListener {
                logg("pressed btLaunchDFIHelp")

                Splitwise.launchHelpDFI(this@MainActivity)
            }
            btLaunchDFSettings.setOnClickListener {
                logg("pressed btLaunchDFSettings")

                Splitwise.launchSettingsDF(this@MainActivity)
            }


        }
    }
}


fun logg(s:String){
    Log.e("APP_APP", s )
}