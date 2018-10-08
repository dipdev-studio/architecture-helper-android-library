package studio.dipdev.ahal.domain.utils.preference

public open interface IAhalPreferenceHelper {

    fun setPreferenceChangesCallback(callback: OnPreferenceChanges)
    fun removePreferenceChangesCallback()

    interface OnPreferenceChanges {
        fun onPreferencesChanges(p1: String)
    }
}