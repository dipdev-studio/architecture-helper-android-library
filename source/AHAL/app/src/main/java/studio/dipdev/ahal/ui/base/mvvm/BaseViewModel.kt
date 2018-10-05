package studio.dipdev.ahal.ui.base.mvvm

import android.annotation.SuppressLint
import android.content.Intent
import androidx.annotation.StringRes
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import studio.dipdev.ahal.ui.base.BaseActivity
import studio.dipdev.ahal.ui.base.BaseFragment


abstract class BaseViewModel<M : BaseModel>(val model: M) : ViewModel(), IBaseViewModel, LifecycleObserver {

    @SuppressLint("StaticFieldLeak")
    internal lateinit var activity: BaseActivity
    internal var fragment: BaseFragment? = null

    open fun init(activity: BaseActivity) {
        this.activity = activity
        activity.lifecycle.addObserver(this)
    }

    open fun init(activity: FragmentActivity, fragment: BaseFragment) {
        if (activity is BaseActivity) {
            this.activity = activity
        } else {
            throw RuntimeException("Activity not is BaseActivity")
        }
        this.fragment = fragment
        activity.lifecycle.addObserver(this)
    }

    override fun getString(@StringRes resId: Int, formatArgs: Any): String {
        return activity.getString(resId, formatArgs)
    }

    override fun getString(@StringRes resId: Int): String {
        return activity.getString(resId)
    }

    override fun onCreated() {}

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    }

    override fun onBackPressed(): Boolean {
        return false
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    override fun start() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    override fun pause() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    override fun resume() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    override fun stop() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    override fun destroy() {
    }
}
