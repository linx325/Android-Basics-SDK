package com.link.basics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.link.sdk.SDK

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        SDK.getSdkDescribe()
    }
}