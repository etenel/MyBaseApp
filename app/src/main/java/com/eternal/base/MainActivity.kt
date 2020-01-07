package com.eternal.base

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.eternal.base.http.RetrofitClient
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() ,CoroutineScope by MainScope(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }
}
