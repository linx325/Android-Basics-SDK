package com.link.basics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.link.sdk.utils.expand.PhoneMode
import com.link.sdk.utils.expand.formatPhone
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val phone = "15056085624"
        test_text.text =  phone.formatPhone(PhoneMode.MODE2)

    }
}