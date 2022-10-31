package com.link.sdk.helper.um

import android.content.Context
import android.content.Intent
import android.os.Parcelable
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import kotlinx.android.parcel.Parcelize

/**
 * @author  张磊  on  2022/03/01 at 19:07
 * Email: 913305160@qq.com
 */
@Parcelize
data class NotifyCustom(

    /**
     * 各种详情页面的 code
     */
    var code: String? = null, // 57dd69c682904a59bbff2ec111fdf937

    /**
     * 通过类型区分跳转的页面:
     *
     *  0-官方比赛发布通知 1-个人比赛通知 2-课程通知 3-处方通知 4-官方比赛开始通知
     *  5-官方比赛结束通知 6-运动结束通知 7-设备绑定 8-设备解绑 9-自由骑行
     */
    var type: Int? = null, // 0
) : Parcelable {

    fun customHandle(context: Context) {
        val intent = Intent().also {}
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent)
    }
}