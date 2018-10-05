package studio.dipdev.ahal.ui.base.mvvm

import android.view.View
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableBoolean
import com.annimon.stream.Stream

open class BaseModel {

    var loading = ObservableBoolean()

    class OnClickCommand : View.OnClickListener, BaseObservable() {

        private val callbackList = ArrayList<(view: View?) -> Unit>()

        fun addCallback(funct: (view: View?) -> Unit) {
            callbackList.add(funct)
        }

        fun clearCallbacks() {
            callbackList.clear()
        }

        override fun onClick(v: View?) {
            Stream.of(callbackList).forEach { it(v) }
        }
    }
}