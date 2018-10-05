package studio.dipdev.ahal.ui.base.mvvm

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProviders
import studio.dipdev.ahal.ui.base.BaseFragment
import studio.dipdev.ahal.BR

@SuppressLint("ValidFragment")
open abstract class BaseBindingFragment<B : ViewDataBinding, VM :
BaseViewModel<M>, M : BaseModel>(@LayoutRes private val layoutId: Int, private val viewModelClass: Class<VM>) :
        BaseFragment() {

    private lateinit var binding: B
    private lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activity = activity
        if (activity != null) {
            viewModel = ViewModelProviders.of(activity).get(viewModelClass)
            viewModel.init(activity, this)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel.onCreated()
        return bind(inflater, container)
    }

    private fun bind(inflater: LayoutInflater, container: ViewGroup?): View? {
        binding = DataBindingUtil.inflate(
                inflater, layoutId, container, false)
        val view = binding.root
        //binding.setVariable(BR.model, viewModel.model)
        binding.executePendingBindings()
        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        viewModel.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }
}