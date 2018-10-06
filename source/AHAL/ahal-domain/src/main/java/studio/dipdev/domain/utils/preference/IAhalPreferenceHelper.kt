package studio.dipdev.domain.utils.preference

interface IAhalPreferenceHelper {

    fun setPreferenceChangesCallback(callback: OnPreferenceChanges)
    fun removePreferenceChangesCallback()

    interface OnPreferenceChanges {
        fun onPreferencesChanges(p1: String)
    }
}