package com.youma.qfy.app.main.um

import android.content.Context
import android.util.Log
import com.blankj.utilcode.util.AppUtils
import com.google.gson.Gson
import com.link.sdk.helper.um.NotifyCustom
import com.umeng.message.UmengNotificationClickHandler
import com.umeng.message.entity.UMessage

/**
 * @author  张磊  on  2022/02/28 at 20:31
 * Email: 913305160@qq.com
 */

/**
 * 友盟消息通知点击的自定义实现
 */
class MyUmengNotificationClickHandler : UmengNotificationClickHandler() {

    private val TAG = "MyUmengNotificationClickHandler"

    /**
     * 当  UMessage.after_open="go_custom" 时，custom 不可为 null
     */
    override fun dealWithCustomAction(context: Context, msg: UMessage) {
        super.dealWithCustomAction(context, msg)
        Log.i(TAG, "dealWithCustomAction: " + msg.raw.toString())

        try {
            //应用在后台时启动应用
            AppUtils.launchApp(context.packageName)

            val notifyCustom = Gson().fromJson(msg.custom, NotifyCustom::class.java)
            notifyCustom.customHandle(context)
        } catch (e: Exception) {
            Log.e(TAG, "dealWithCustomAction: 处理点击通知出错", e)
        }
    }

    /**
     * 打开 Activity 时回调, UMessage.after_open == "go_activity" , 此时 activity 不可为 null
     */
    override fun openActivity(context: Context?, msg: UMessage) {
        super.openActivity(context, msg)
        Log.i(TAG, "click openActivity: " + msg.raw.toString())
    }

    /**
     * 启动 App 时回调
     */
    override fun launchApp(context: Context?, msg: UMessage) {
        super.launchApp(context, msg)
        Log.i(TAG, "click launchApp: " + msg.raw.toString())
    }

    /**
     * 消息消失时回调
     */
    override fun dismissNotification(context: Context?, msg: UMessage) {
        super.dismissNotification(context, msg)
        Log.i(TAG, "click dismissNotification: " + msg.raw.toString())
    }
}