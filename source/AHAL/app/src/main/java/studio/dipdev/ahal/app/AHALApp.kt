package studio.dipdev.ahal.app

import android.annotation.SuppressLint
import androidx.multidex.MultiDexApplication


@SuppressLint("Registered")
open class AHALApp : MultiDexApplication() {
    override fun onCreate() {
        ApplicationComponent.init(this).inject(this)
        super.onCreate()
    }
}