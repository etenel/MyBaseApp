package com.eternal.base.app

import android.net.ParseException
import android.text.TextUtils
import com.blankj.utilcode.util.ToastUtils
import com.google.gson.JsonParseException
import io.reactivex.exceptions.UndeliverableException
import kotlinx.coroutines.CoroutineExceptionHandler
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import kotlin.coroutines.CoroutineContext

object GlobalCoroutineExceptionHandler : CoroutineExceptionHandler {
    override val key: CoroutineContext.Key<*>
        get() = CoroutineExceptionHandler //To change initializer of created properties use File | Settings | File Templates.

    override fun handleException(context: CoroutineContext, exception: Throwable) {
        if (exception is UndeliverableException) {
            return
        }
        //这里不光只能打印错误, 还可以根据不同的错误做出不同的逻辑处理
        //这里只是对几个常用错误进行简单的处理, 展示这个类的用法, 在实际开发中请您自行对更多错误进行更严谨的处理
        var msg = "未知错误"
        if (!TextUtils.isEmpty(exception.message)) {
            msg = exception.message.orEmpty()
        }
        if (exception is UnknownHostException) {
            msg = "网络不可用"
        } else if (exception is SocketTimeoutException) {
            msg = "请求网络超时"
        } else if (exception is HttpException) {
            msg = convertStatusCode(exception)
        } else if (exception is JsonParseException || exception is ParseException || exception is JSONException) {
            msg = "数据解析错误"
        } else if (exception is ConnectException) {
            msg = "连接服务器失败"
        } else if (exception is NullPointerException) {
            msg = ""
        }
        if (!TextUtils.isEmpty(msg)) {
            ToastUtils.showShort(msg)
        }
    }

    private fun convertStatusCode(httpException: HttpException): String {
        val msg: String
        if (httpException.code() == 500) {
            msg = "服务器发生错误"
        } else if (httpException.code() == 404) {
            msg = "请求地址不存在"
        } else if (httpException.code() == 403) {
            msg = "请求被服务器拒绝"
        } else if (httpException.code() == 307) {
            msg = "请求被重定向到其他页面"
        } else {
            msg = httpException.message()
        }
        return msg
    }
}