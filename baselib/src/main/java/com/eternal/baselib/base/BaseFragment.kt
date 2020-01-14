package com.eternal.baselib.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import java.lang.reflect.ParameterizedType

abstract class BaseFragment<V : ViewDataBinding, VM : BaseViewModel<*>>() :
    Fragment(), IFragment {
    protected lateinit var binding: V
    protected lateinit var viewModel: VM
    private var viewModelId: Int = 0
    lateinit var mContext: Context
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layoutRes = initView()
        if (layoutRes != 0) {
            binding = DataBindingUtil.inflate(
                inflater,
                initView(),
                container,
                false
            )
            return binding.root
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //私有的初始化ViewModel方法
        activity?.application?.let {
            val modelClass: Class<VM> =
                (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1] as Class<VM>
            viewModel=ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(it)).get(modelClass)
        }
        viewModel.injectLifecycle(lifecycle)
        //私有的ViewModel与View的契约事件回调逻辑
        registerUIChangeLiveDataCallBack()
        //页面事件监听的方法，一般用于ViewModel层转到View层的事件注册
        initViewObservable()
        //页面数据初始化方法
        initData(savedInstanceState)

        binding.lifecycleOwner = this
        viewModelId=initVariableId()
        if (viewModelId != 0) {
            binding.setVariable(viewModelId, viewModel)
        }
    }
    private fun registerUIChangeLiveDataCallBack() {
        viewModel.ui.showDialogEvent.observe(viewLifecycleOwner, Observer {
            // showDialog(it)
        })
        viewModel.ui.dismissDialogEvent.observe(viewLifecycleOwner, Observer {
            //            dismissDialog()
        })
        viewModel.ui.finishEvent.observe(viewLifecycleOwner, Observer {
           activity?.finish()
        })
        viewModel.ui.onBackPressedEvent.observe(viewLifecycleOwner, Observer {
            activity?.onBackPressed()
        })
        viewModel.ui.finishRefreshEvent.observe(viewLifecycleOwner, Observer {
            hideRefresh()
        })
        viewModel.ui.finishLoadMoreEvent.observe(viewLifecycleOwner, Observer {
            hideLoadMore()
        })
    }
    override fun onDetach() {
        super.onDetach()
        binding.unbind()
    }
}