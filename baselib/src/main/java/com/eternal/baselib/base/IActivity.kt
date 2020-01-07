package com.eternal.baselib.base

import android.os.Bundle

interface IActivity {


    /**
     * 初始化数据
     */
    fun initData(savedInstanceState: Bundle?)

    fun hideRefresh()
    fun hideLoadMore()
    /**
     * 初始化界面观察者的监听
     */
    fun initViewObservable()

    fun initView(): Int
    fun initVariableId(): Int
}