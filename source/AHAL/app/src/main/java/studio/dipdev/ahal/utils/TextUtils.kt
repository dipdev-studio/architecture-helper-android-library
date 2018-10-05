package studio.dipdev.ahal.utils

class TextUtils {


    fun isValidEmail(target: CharSequence?): Boolean {
        return target != null && !android.text.TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }

    fun isValidEmpty(target: CharSequence?): Boolean {
        return target != null && !android.text.TextUtils.isEmpty(target)
    }

    fun isValidIdentical(target: CharSequence?, target2: CharSequence?): Boolean {
        return target != null && target2 != null && target2 == target
    }
}