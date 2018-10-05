package studio.dipdev.ahal.ui.base

import androidx.fragment.app.Fragment


abstract class BaseFragment : Fragment() {
    abstract fun handleError(error: Throwable?)
}