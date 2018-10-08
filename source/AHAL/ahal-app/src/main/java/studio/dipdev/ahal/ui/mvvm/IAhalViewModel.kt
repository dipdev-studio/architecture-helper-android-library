package studio.dipdev.ahal.ui.mvvm

import android.content.Intent
import androidx.annotation.StringRes

open interface IAhalViewModel {

    fun getString(@StringRes resId: Int, formatArgs: Any): String
    fun getString(@StringRes resId: Int): String
    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    fun onBackPressed(): Boolean
    fun start()
    fun pause()
    fun resume()
    fun stop()
    fun destroy()
    fun onCreated()

}