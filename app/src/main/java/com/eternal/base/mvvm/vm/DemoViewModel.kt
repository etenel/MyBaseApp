package com.eternal.base.mvvm.vm

import android.app.Application
import com.eternal.base.mvvm.model.DemoModel
import com.eternal.baselib.base.BaseViewModel


class DemoViewModel(application: Application) : BaseViewModel(application) {
    init {
        model = DemoModel()
    }

    override fun onCleared() {
        super.onCleared()
    }

}
