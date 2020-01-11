package com.eternal.base

import android.app.Application
import com.eternal.baselib.base.BaseViewModel

class MainViewModel(application: Application) : BaseViewModel<MainModel>(application) {
    init {
        model=MainModel()
    }

}