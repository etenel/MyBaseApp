package com.eternal.base

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.eternal.base.http.RetrofitClient
import com.eternal.base.mvvm.ui.activity.WebViewActivity
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent = Intent(this, WebViewActivity::class.java)
        intent.putExtra("url","https://github.com/")
        startActivity(intent)
    }

}
