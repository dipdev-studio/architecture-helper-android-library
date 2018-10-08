package studio.dipdev.ahal.ui.mvvm

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.bumptech.glide.Glide
import com.google.android.material.textfield.TextInputLayout


public object AhalBindingAdapters {

    @JvmStatic
    @BindingAdapter("app:src")
    fun bindImageViewUrl(view: ImageView, url: String?) {
        if (url != null) Glide.with(view.context)
                .load(url)
                .fitCenter()
                //.placeholder(R.drawable.custom_progressbar)
                .crossFade()
                .into(view)
    }

    @JvmStatic
    @BindingAdapter("app:onClick")
    fun bindOnClick(view: View, onClickListener: View.OnClickListener) {
        view.setOnClickListener(onClickListener)
    }

    @JvmStatic
    @BindingAdapter("app:text")
    fun bindEditText(view: EditText, value: String) {
        if (view.text.toString() != value) view.setText(value)
    }

    @JvmStatic
    @BindingAdapter("android:textAttrChanged")
    fun bindEditText(view: EditText, textAttrChanged: InverseBindingListener) {
        view.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {
                textAttrChanged.onChange()
            }
        })
    }

    @JvmStatic
    @InverseBindingAdapter(attribute = "app:text", event = "android:textAttrChanged")
    fun bindEditTextInverse(view: EditText): String {
        return view.text.toString()
    }

    @JvmStatic
    @BindingAdapter("app:errorText")
    fun bindTextInputLayout(view: TextInputLayout, errorMessage: String?) {
        view.error = errorMessage
    }
}