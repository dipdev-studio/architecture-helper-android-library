package studio.dipdev.ahal.ui.mvvm

import android.annotation.SuppressLint
import android.content.Intent
import androidx.databinding.ViewDataBinding
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import studio.dipdev.ahal.ui.AhalActivity


@SuppressLint("Registered")
public open abstract class AhalBindingActivity<B : ViewDataBinding, VM :
AhalViewModel<M>, M : AhalModel>(@LayoutRes private val layoutId: Int, private val viewModelClass: Class<VM>) :
        AhalActivity() {
    private lateinit var binding: B
    private lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(viewModelClass)
        viewModel.init(this)
        viewModel.onCreated()
        bind()
    }

    private fun bind() {
        binding = DataBindingUtil.setContentView(this, layoutId)
        //binding.setVariable(BR.model, viewModel.model)
        binding.executePendingBindings()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        viewModel.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onBackPressed() {
        if (!viewModel.onBackPressed()) {
            super.onBackPressed()
        }
    }

    fun getBinding(): B {
        return binding
    }
}