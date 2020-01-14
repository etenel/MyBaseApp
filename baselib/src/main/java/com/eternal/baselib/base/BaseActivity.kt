package com.eternal.baselib.base

import android.os.Bundle
import android.view.InflateException
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

@Suppress("UNCHECKED_CAST")
abstract class BaseActivity<V : ViewDataBinding, VM : BaseViewModel<*>>() :
    AppCompatActivity(), IActivity {
    protected lateinit var binding: V
    protected lateinit var viewModel: VM
    private var viewModelId: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            val layoutResID: Int = initView()
            if (layoutResID != 0) {
                binding = DataBindingUtil.setContentView(this, layoutResID)
            }
        } catch (e: Exception) {
            if (e is InflateException) throw e
            e.printStackTrace()
        }
        viewModelId = initVariableId()
        //私有的初始化ViewModel方法
        val modelClass: Class<VM> = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1] as Class<VM>
        viewModel=ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(modelClass)
        viewModel.injectLifecycle(lifecycle)
        //私有的ViewModel与View的契约事件回调逻辑
        registerUIChangeLiveDataCallBack()
        //页面事件监听的方法，一般用于ViewModel层转到View层的事件注册
        initViewObservable()
        //页面数据初始化方法
        initData(savedInstanceState)

        binding.lifecycleOwner = this
        if (viewModelId != 0) {
            binding.setVariable(viewModelId, viewModel)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        binding.unbind()
    }
    private fun registerUIChangeLiveDataCallBack() {
        viewModel.ui.showDialogEvent.observe(this, Observer {
            // showDialog(it)
        })
        viewModel.ui.dismissDialogEvent.observe(this, Observer {
            //            dismissDialog()
        })
        viewModel.ui.finishEvent.observe(this, Observer {
            finish()
        })
        viewModel.ui.onBackPressedEvent.observe(this, Observer {
            onBackPressed()
        })
        viewModel.ui.finishRefreshEvent.observe(this, Observer {
            hideRefresh()
        })
        viewModel.ui.finishLoadMoreEvent.observe(this, Observer {
            hideLoadMore()
        })
    }

}