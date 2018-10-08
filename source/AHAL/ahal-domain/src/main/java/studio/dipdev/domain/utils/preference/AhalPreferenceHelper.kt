package studio.dipdev.domain.utils.preference

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.gson.Gson

public open class AhalPreferenceHelper(private val context: Context?) : IAhalPreferenceHelper {

    private val gson = Gson()
    private var onPreferenceChangesCallback: IAhalPreferenceHelper.OnPreferenceChanges? = null

    private var onSharedPreferenceChangeListener = SharedPreferences.OnSharedPreferenceChangeListener { _, p1 ->
        onPreferenceChangesCallback?.onPreferencesChanges(p1)
    }

    override fun setPreferenceChangesCallback(callback: IAhalPreferenceHelper.OnPreferenceChanges) {
        onPreferenceChangesCallback = callback
        provideSharedPreferences().registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener)
    }

    override fun removePreferenceChangesCallback() {
        provideSharedPreferences().unregisterOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener)
        onPreferenceChangesCallback = null
    }

    private fun provideSharedPreferences(): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    private fun provideSharedPreferencesEditor(): SharedPreferences.Editor {
        return provideSharedPreferences().edit()
    }

    fun setBoolean(mKey: String, mValue: Boolean) {
        val editor = provideSharedPreferencesEditor()
        editor.putBoolean(mKey, mValue)
        editor.apply()
    }

    fun getBoolean(mKey: String, mDefValue: Boolean): Boolean {
        return provideSharedPreferences().getBoolean(mKey, mDefValue)
    }

    fun setInt(mKey: String, mValue: Int) {
        val editor = provideSharedPreferencesEditor()
        editor.putInt(mKey, mValue)
        editor.apply()
    }

    fun getInt(mKey: String, mDefValue: Int): Int {
        return provideSharedPreferences().getInt(mKey, mDefValue)
    }

    fun setFloat(mKey: String, mValue: Float) {
        val editor = provideSharedPreferencesEditor()
        editor.putFloat(mKey, mValue)
        editor.apply()
    }

    fun getFloat(mKey: String, mDefValue: Float): Float {
        return provideSharedPreferences().getFloat(mKey, mDefValue)
    }

    fun setLong(mKey: String, mValue: Long) {
        val editor = provideSharedPreferencesEditor()
        editor.putLong(mKey, mValue)
        editor.apply()
    }

    fun getLong(mKey: String, mDefValue: Long): Long {
        return provideSharedPreferences().getLong(mKey, mDefValue)
    }

    fun setString(mKey: String, mValue: String) {
        val editor = provideSharedPreferencesEditor()
        editor.putString(mKey, mValue)
        editor.apply()
    }

    fun getString(mKey: String, mDefValue: String): String? {
        return provideSharedPreferences().getString(mKey, mDefValue)
    }

    fun remove(mKey: String) {
        provideSharedPreferencesEditor().remove(mKey).apply()
    }

    fun <T> setEntity(mKey: String, mValue: Class<T>) {
        val json = gson.toJson(mValue)
        setString(mKey, json)
    }

    fun <T> getEntity(mKey: String, mValue: Class<T>): Class<T>? {
        val json = getString(mKey, "")
        return try {
            gson.fromJson(json, mValue::class.java)
        } catch (e: Exception) {
            null
        }
    }

    fun setStringSet(mKey: String, mValue: Set<String>) {
        val editor = provideSharedPreferencesEditor()
        editor.putStringSet(mKey, mValue)
        editor.apply()
    }

    fun getStringSet(mKey: String, mDefValue: Set<String>): Set<String>? {
        return provideSharedPreferences().getStringSet(mKey, mDefValue)
    }
}
