package com.eternal.base.mvvm.ui.activity

import android.os.Bundle

import com.eternal.baselib.base.BaseActivity

import com.eternal.base.mvvm.vm.DemoViewModel
import com.eternal.base.databinding.ActivityDemoBinding
import com.eternal.base.R
import com.eternal.base.BR


class DemoActivity : BaseActivity<ActivityDemoBinding, DemoViewModel>() {

    override fun initView(): Int {
        return R.layout.activity_demo
    }


    override fun initVariableId(): Int {
        return BR.vm
    }

    override fun initData(savedInstanceState: Bundle?) {

    }


    override fun hideRefresh() {

    }

    override fun hideLoadMore() {

    }

    override fun initViewObservable() {

    }
}
