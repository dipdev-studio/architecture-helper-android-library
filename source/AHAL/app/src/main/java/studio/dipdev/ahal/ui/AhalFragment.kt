package studio.dipdev.ahal.ui

import androidx.fragment.app.Fragment


abstract class AhalFragment : Fragment() {
    abstract fun handleError(error: Throwable?)
}