package work.curioustools.tdfm

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.google.android.play.core.splitinstall.SplitInstallSessionState
import com.google.android.play.core.splitinstall.SplitInstallStateUpdatedListener
import com.google.android.play.core.splitinstall.model.SplitInstallErrorCode
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus
import com.google.android.play.core.tasks.Task

object Splitwise {

    fun getInstalledModules(ctx: Context):String{
        return SplitInstallManagerFactory.create(ctx).installedModules.joinToString(",")
    }
    fun installModuleInBackground(ctx:Context,onComplete:(Task<Void?>)->Unit){
        SplitInstallManagerFactory.create(ctx).deferredInstall(myModules).addOnCompleteListener(onComplete)
    }

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

     fun logState(it: SplitInstallSessionState) {
        val status = "status:"+ when (it.status()) {
            SplitInstallSessionStatus.CANCELED -> "CANCELED"
            SplitInstallSessionStatus.CANCELING -> "CANCELING"
            SplitInstallSessionStatus.DOWNLOADED -> "DOWNLOADED"
            SplitInstallSessionStatus.DOWNLOADING -> "DOWNLOADING"
            SplitInstallSessionStatus.FAILED -> "FAILED"
            SplitInstallSessionStatus.INSTALLED -> "INSTALLED"
            SplitInstallSessionStatus.INSTALLING -> "INSTALLING"
            SplitInstallSessionStatus.PENDING -> "PENDING"
            SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION -> "REQUIRES_USER_CONFIRMATION"
            SplitInstallSessionStatus.UNKNOWN -> "UNKNOWN"
            else -> "??${it.status()}"
        }
        val bytes = "${ it.bytesDownloaded() }/${it.totalBytesToDownload()}Bytes"
        val d ="error:"+ when(it.errorCode()){
            SplitInstallErrorCode.ACCESS_DENIED -> "ACCESS_DENIED"
            SplitInstallErrorCode.ACTIVE_SESSIONS_LIMIT_EXCEEDED -> "ACTIVE_SESSIONS_LIMIT_EXCEEDED"
            SplitInstallErrorCode.API_NOT_AVAILABLE -> "API_NOT_AVAILABLE"
            SplitInstallErrorCode.APP_NOT_OWNED -> "APP_NOT_OWNED"
            SplitInstallErrorCode.INCOMPATIBLE_WITH_EXISTING_SESSION ->"INCOMPATIBLE_WITH_EXISTING_SESSION"
            SplitInstallErrorCode.INSUFFICIENT_STORAGE ->"INSUFFICIENT_STORAGE"
            SplitInstallErrorCode.INTERNAL_ERROR -> "INTERNAL_ERROR"
            SplitInstallErrorCode.INVALID_REQUEST -> "INVALID_REQUEST"
            SplitInstallErrorCode.MODULE_UNAVAILABLE -> "MODULE_UNAVAILABLE"
            SplitInstallErrorCode.NETWORK_ERROR -> "NETWORK_ERROR"
            SplitInstallErrorCode.NO_ERROR ->"NO_ERROR"
            SplitInstallErrorCode.PLAY_STORE_NOT_FOUND -> "PLAY_STORE_NOT_FOUND"
            SplitInstallErrorCode.SERVICE_DIED -> "SERVICE_DIED"
            SplitInstallErrorCode.SESSION_NOT_FOUND -> "SESSION_NOT_FOUND"
            SplitInstallErrorCode.SPLITCOMPAT_COPY_ERROR ->"SPLITCOMPAT_COPY_ERROR"
            SplitInstallErrorCode.SPLITCOMPAT_EMULATION_ERROR -> "SPLITCOMPAT_EMULATION_ERROR"
            SplitInstallErrorCode.SPLITCOMPAT_VERIFICATION_ERROR -> "SPLITCOMPAT_VERIFICATION_ERROR"
            else ->"??(${it.errorCode()})"
        }
        val p = "has_terminal_status:${it.hasTerminalStatus()}"
        val q = "modules:${it.moduleNames()}"
        val r = "session id : ${it.sessionId()}"
        listOf(status,bytes,d,p,q,r).forEach {i -> logg(i) }
    }

    fun isDownloadingAModule(ctx:Context,onComplete: (SplitInstallSessionState) -> Unit){
        SplitInstallManagerFactory.create(ctx) .sessionStates.addOnCompleteListener {
           val result = it.result

           result.forEach {packet->
               if (packet.status() == SplitInstallSessionStatus.DOWNLOADING) {
                   onComplete.invoke(packet)
               }

           }
        }
    }

    fun cancelDownloadingAModule(ctx: Context, sessionID:Int){
        SplitInstallManagerFactory.create(ctx).cancelInstall(sessionID)
    }

    fun launchHelpDFI(activity: Activity){
        activity.startActivity(Intent().setClassName("work.curioustools.tdfm", "work.curioustools.dfi_help.HelpActivity"))
    }
    fun launchSettingsDF(activity: Activity){
        activity.startActivity(Intent().setClassName("work.curioustools.tdfm", "work.curioustools.df_setting.SettingsActivity"))
    }

}