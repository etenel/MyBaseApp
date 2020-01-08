package com.eternal.base.mvvm.ui.activity

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import com.eternal.baselib.base.BaseActivity
import com.eternal.base.mvvm.vm.WebViewViewModel
import com.eternal.base.databinding.ActivityWebViewBinding;
import com.eternal.base.R
import com.eternal.base.BR
import kotlinx.android.synthetic.main.activity_web_view.*


class WebViewActivity : BaseActivity<ActivityWebViewBinding, WebViewViewModel>() {
    var webview: WebView? = null
    override fun initView(): Int {
        return R.layout.activity_web_view //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initVariableId(): Int {
        return BR.vm
    }

    override fun initData(savedInstanceState: Bundle?) {
        val url = intent.getStringExtra("url").orEmpty()
        webview = WebView(getFixedContext(this))

        webview?.settings?.apply {
            javaScriptEnabled = false
            javaScriptCanOpenWindowsAutomatically = false
            allowFileAccess = false
            //   layoutAlgorithm = WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING
            useWideViewPort = true
            loadWithOverviewMode = true
            setSupportMultipleWindows(true)
            setGeolocationEnabled(true)
            setSupportZoom(true)
            builtInZoomControls = true
            displayZoomControls = false
            setAppCacheEnabled(true)
            domStorageEnabled = true
            cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
            //  mixedContentMode=WebSettings.MIXED_CONTENT_NEVER_ALLOW
        }
        cl_content.addView(webview)
        webview?.loadUrl(url)
    }

    //修复androidx 部分5.1机型 webview 报Resources$NotFoundException闪退问题
    private fun getFixedContext(context: Context): Context {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return context.createConfigurationContext(Configuration())
        }
        return context
    }

    override fun onDestroy() {
        webview?.loadDataWithBaseURL(null, "", "text/html", "utf-8", null)
        webview?.clearHistory()
        (webview?.parent as ViewGroup).removeView(webview)
        webview?.destroy()
        webview = null
        super.onDestroy()

    }

    override fun hideRefresh() {

    }

    override fun hideLoadMore() {

    }

    override fun initViewObservable() {

    }
}
