package com.eternal.base.app

import android.app.Activity
import android.app.Application
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.eternal.base.R

class ActivityLifecycleCallbacksImpl :Application.ActivityLifecycleCallbacks {
    override fun onActivityPaused(activity: Activity) {

    }

    override fun onActivityStarted(activity: Activity) {
        if (!activity.intent.getBooleanExtra(
                "isInitToolbar",
                false
            )
        ) {
            //由于加强框架的兼容性,故将 setContentView 放到 onActivityCreated 之后,onActivityStarted 之前执行
//而 findViewById 必须在 Activity setContentView() 后才有效,所以将以下代码从之前的 onActivityCreated 中移动到 onActivityStarted 中执行
            activity.intent.putExtra("isInitToolbar", true)
            //这里全局给Activity设置toolbar和title,你想象力有多丰富,这里就有多强大,以前放到BaseActivity的操作都可以放到这里
            if (activity.findViewById<View?>(R.id.toolbar) != null) {
                if (activity is AppCompatActivity) {
                    activity.setSupportActionBar(
                        activity.findViewById<View>(
                            R.id.toolbar
                        ) as Toolbar
                    )
                    activity.supportActionBar?.setDisplayShowTitleEnabled(false)
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        activity.setActionBar(activity.findViewById<View>(R.id.toolbar) as android.widget.Toolbar)
                        activity.actionBar?.setDisplayShowTitleEnabled(false)
                    }
                }
            }
            if (activity.findViewById<View?>(R.id.toolbar_title) != null) {
                (activity.findViewById<View>(R.id.toolbar_title) as TextView).text = activity.title
            }
            if (activity.findViewById<View?>(R.id.toolbar_back) != null) {
                activity.findViewById<View>(R.id.toolbar_back)
                    .setOnClickListener { activity.onBackPressed() }
            }
        }
    }

    override fun onActivityDestroyed(activity: Activity) {
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
    }

    override fun onActivityStopped(activity: Activity) {
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
    }

    override fun onActivityResumed(activity: Activity) {
    }
}