package studio.dipdev.ahal.ui

import androidx.fragment.app.Fragment

open abstract class AhalFragment : Fragment() {
    abstract fun handleError(error: Throwable?)
}