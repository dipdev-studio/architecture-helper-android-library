package studio.dipdev.ahal.ui.base.mvvm

import android.annotation.SuppressLint
import android.content.Intent
import androidx.databinding.ViewDataBinding
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import studio.dipdev.ahal.ui.base.BaseActivity
import studio.dipdev.ahal.BR


@SuppressLint("Registered")
open abstract class BaseBindingActivity<B : ViewDataBinding, VM :
BaseViewModel<M>, M : BaseModel>(@LayoutRes private val layoutId: Int, private val viewModelClass: Class<VM>) :
        BaseActivity() {
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