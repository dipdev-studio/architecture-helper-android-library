package studio.dipdev.ahal.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity

@SuppressLint("Registered")
public open abstract class AhalActivity : AppCompatActivity() {

/*    @SuppressLint("CommitTransaction")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            with(window) {
                requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
                window.allowEnterTransitionOverlap = true
                exitTransition = Explode()
            }
        }
    }*/

    fun navigateTo(clazz: Class<*>, clear: Boolean) {
        startActivity(getIntent(clazz, clear, null))
    }

    fun navigateTo(clazz: Class<*>, clear: Boolean, extras: HashMap<String, String>) {
        startActivity(getIntent(clazz, clear, extras))
    }

    fun switchFragment(@IdRes resourceId: Int, fragment: AhalFragment, replace: Boolean, backStack: Boolean) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        if (replace) fragmentTransaction.replace(resourceId, fragment)
        else fragmentTransaction.add(resourceId, fragment)
        if (backStack) fragmentTransaction.addToBackStack(null)

        fragmentTransaction.commit()
    }

    private fun getIntent(clazz: Class<*>, clear: Boolean, extras: HashMap<String, String>?): Intent {
        val intent = Intent(this, clazz)
        if (clear) intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        if (extras != null) {
            for ((key, value) in extras) {
                intent.putExtra(key, value)
            }
        }
        return intent
    }

    abstract fun handleError(error: Throwable?)

}