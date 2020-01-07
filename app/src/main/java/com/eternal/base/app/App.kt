package com.eternal.base.app

import android.app.Application
import android.content.Context
import kotlin.properties.Delegates

class App:Application() {
    companion object {
        var CONTEXT: Context by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        CONTEXT = applicationContext
        registerActivityLifecycleCallbacks(ActivityLifecycleCallbacksImpl())
//        startKoin {
//            androidContext(this@App)
//            modules(appModule)
//        }
    }

}