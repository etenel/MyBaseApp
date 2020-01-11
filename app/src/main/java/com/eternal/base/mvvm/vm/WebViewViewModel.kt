package com.eternal.base.mvvm.vm

import android.app.Application
import com.eternal.base.mvvm.model.WebViewModel
import com.eternal.baselib.base.BaseViewModel


class WebViewViewModel(application: Application) : BaseViewModel<WebViewModel>(application) {


    init {
        model = WebViewModel()
    }

    override fun onCleared() {
        super.onCleared()
    }

}
