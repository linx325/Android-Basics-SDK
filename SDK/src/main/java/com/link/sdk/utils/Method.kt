package com.link.sdk.utils

import java.security.MessageDigest
import java.util.regex.Pattern


/**
 * 是否是手机号
 * 如果不是号码，则返回false，是号码则返回true
 */
fun String.isPhoneNumber(): Boolean {
    val regex =
        "^((13[0-9])|(14[0-9])|(15[0-9])|(16[0-9])|(17[0-9])|(18[0-9])|(19[0-9]))\\d{8}$"
    return Pattern.matches(regex, this)
}
