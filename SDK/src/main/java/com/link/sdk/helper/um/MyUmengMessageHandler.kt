package com.youma.qfy.app.main.um

import android.app.Notification
import android.content.Context
import android.os.Handler
import android.os.Looper.getMainLooper
import android.util.Log
import com.umeng.message.UTrack
import com.umeng.message.UmengMessageHandler
import com.umeng.message.entity.UMessage

/**
 * @author  张磊  on  2022/03/01 at 14:57
 * Email: 913305160@qq.com
 */
class MyUmengMessageHandler(val context: Context) : UmengMessageHandler() {

	private val TAG = "MyUmengMessageHandler"

	//收到通知栏消息时,  UMessage.display_type == "notification"
	override fun dealWithNotificationMessage(context: Context?, msg: UMessage) {
		super.dealWithNotificationMessage(context, msg)
		Log.i(TAG, "收到通知:" + msg.raw.toString())
	}

	//自定义通知样式，此方法可以修改通知样式等
	override fun getNotification(context: Context?, msg: UMessage?): Notification? {
		return super.getNotification(context, msg)
	}

	//收到自定义的消息时， UMessage.display_type == "custom"
	override fun dealWithCustomMessage(context: Context?, msg: UMessage) {
		super.dealWithCustomMessage(context, msg)
		Log.i(TAG, "收到自定义消息通知:" + msg.raw.toString())

		Handler(getMainLooper()).post {

			// 对于自定义消息，PushSDK默认只统计送达。若开发者需要统计点击和忽略，则需手动调用统计方法。
			val isClickOrDismissed = true
			if (isClickOrDismissed) {
				//自定义消息的点击统计
				UTrack.getInstance(context).trackMsgClick(msg)
			} else {
				//自定义消息的忽略统计
				UTrack.getInstance(context).trackMsgDismissed(msg)
			}
		}
	}

}