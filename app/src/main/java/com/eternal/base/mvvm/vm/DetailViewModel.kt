package com.eternal.base.mvvm.vm

import android.app.Application
import com.eternal.base.mvvm.model.DetailModel
import com.eternal.baselib.base.BaseViewModel


class DetailViewModel(application: Application) : BaseViewModel(application) {


    init {
        model = DetailModel()
    }

    override fun onCleared() {
        super.onCleared()
    }

}
