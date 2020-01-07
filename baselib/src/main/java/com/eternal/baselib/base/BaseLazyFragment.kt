package com.eternal.baselib.base

import androidx.databinding.ViewDataBinding

abstract class BaseLazyFragment<V : ViewDataBinding, VM : BaseViewModel>() :
    BaseFragment<V,VM>(), IFragment {
    var isViewCreated: Boolean = false
    var isDataLoaded: Boolean = false
    var viewDestroy=false
    /**
     * 第一次可见时触发调用,此处实现具体的数据请求逻辑
     */
    protected abstract fun lazyLoadData()

    /**
     * 保证在initData后触发
     */
    override fun onResume() {
        super.onResume()
        isViewCreated = true
        viewDestroy = false
        tryLoadData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewDestroy = true
    }
    private  fun tryLoadData() {
        if (isViewCreated && !isDataLoaded) {
            lazyLoadData()
            isDataLoaded = true
        }
    }
}