package com.link.sdk.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
object DateUtils {

    private const val DATE_FORMAT_YMD = "yyyy-MM-dd"
    private const val DATE_FORMAT_YMD_HMS = "yyyy-MM-dd HH:mm:ss"

    /**
     * 当前时间的时间戳
     */
    fun current(): Long {
        return System.currentTimeMillis()
    }

    /**
     * 格式化时间戳
     * yyyy-MM-dd
     */

    fun formatDate(timestamp: Long): String {
        return if (timestamp == 0L) "-" else SimpleDateFormat(DATE_FORMAT_YMD).format(Date(timestamp))
    }

    /**
     * 格式化时间戳
     * yyyy-MM-dd HH:mm:ss
     */
    fun formatDate(timestamp: Long = current(), simple: String = DATE_FORMAT_YMD_HMS): String {
        return if (timestamp == 0L) "-" else SimpleDateFormat(simple).format(Date(timestamp))
    }

    /**
     * 今天是哪年哪月哪日
     *
     * 示例:2022-09-26
     */
    fun getToday(simple: String = DATE_FORMAT_YMD): String {
        return SimpleDateFormat(simple).format(Date(current()))
    }

    /**
     * 今天是哪年哪月哪日几点几分几秒
     *
     * 示例:2022-09-26 10:17:45
     */
    fun getTodayTime(): String {
        return getToday(DATE_FORMAT_YMD_HMS)
    }


    /**
     * 将秒转化为00:00:00格式
     */
    fun seconds2Str(second: Long): String {
        if (second == 0L) {
            return ""
        }
        val seconds = second % 60
        val minutes = (second / 60) % 60
        val hours = second / 3600
        return Formatter().format("%02d:%02d:%02d", hours, minutes, seconds).toString()
    }
}