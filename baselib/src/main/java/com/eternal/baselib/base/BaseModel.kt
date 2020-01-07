package com.eternal.baselib.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

open class BaseModel : IModel, LifecycleObserver {


    override fun onDestroy() {

    }
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy(owner: LifecycleOwner){
        owner.lifecycle.removeObserver(this)
    }
}