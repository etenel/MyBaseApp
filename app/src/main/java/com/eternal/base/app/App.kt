package com.eternal.base.app

import android.app.Application
import android.content.Context
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.Utils
import com.eternal.base.BuildConfig
import kotlin.properties.Delegates

class App:Application() {
    companion object {
        var CONTEXT: Context by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        CONTEXT = applicationContext
        registerActivityLifecycleCallbacks(ActivityLifecycleCallbacksImpl())
        Utils.init(this)
        LogUtils.getConfig().setLogSwitch(BuildConfig.LOG_DEBUG)
            .setConsoleSwitch(BuildConfig.LOG_DEBUG)
//        startKoin {
//            androidContext(this@App)
//            modules(appModule)
//        }
    }

}