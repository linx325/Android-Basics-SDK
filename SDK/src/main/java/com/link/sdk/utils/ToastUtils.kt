package com.link.sdk.utils

import android.content.Context
import android.text.TextUtils
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.link.sdk.R
import com.link.sdk.SDKApplication

/**
 * 对Toast进行去重
 */
object ToastUtils {
    private var toast: Toast? = null

    /**
     * 吐司提示
     */
    fun showToast(msg: String?): Toast? {
        return showToast(SDKApplication.instance, msg)
    }

    /**
     * 吐司提示
     */
    fun showToast(ctx: Context?, msg: String?): Toast? {
        if (toast != null) {
            toast!!.cancel()
        }
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.layout_sdk_toast_view, null)
        val textView: TextView = view.findViewById<TextView>(R.id.toast_view_text)
        textView.text = if (TextUtils.isEmpty(msg)) "" else msg
        toast = Toast(ctx)
        toast!!.view = view
        toast!!.setGravity(Gravity.CENTER, 0, 0)
        toast!!.duration = Toast.LENGTH_SHORT
        toast!!.show()
        return toast
    }
}