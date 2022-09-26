package com.link.sdk.utils.expand

import java.util.regex.Pattern

/**
 * 是否是手机号
 *
 * 如果不是号码，则返回false，是号码则返回true
 */
fun String.isPhoneNumber(): Boolean {
    val regex =
        "^((13[0-9])|(14[0-9])|(15[0-9])|(16[0-9])|(17[0-9])|(18[0-9])|(19[0-9]))\\d{8}$"
    return Pattern.matches(regex, this)
}

/**
 * 格式化手机号
 *
 * mode1: 138****5678
 *
 * mode2: 138-1234-5678
 */
fun String.formatPhone(mode: PhoneMode = PhoneMode.MODE1): String {
    if (this.length == 11) {
        when (mode) {
            PhoneMode.MODE1 -> {
                val sb = StringBuffer()
                sb.append(this.substring(0, 3))
                val sub = this.substring(3, 7)
                sb.append(sub.replace(sub, "****"))
                sb.append(this.substring(7, 11))
                return sb.toString()
            }
            PhoneMode.MODE2 -> {
                val sb = StringBuffer()
                sb.append(this.substring(0, 3))
                sb.append("-")
                sb.append(this.substring(3, 7))
                sb.append("-")
                sb.append(this.substring(7, 11))
                return sb.toString()
            }
            else -> {
                return this
            }
        }
    }
    return this
}

/**
 * 空字符展示默认值
 */
fun String.notNull(simple: String = "-"): String {
    if (this.isEmpty()) {
        return simple
    }
    return this
}

enum class PhoneMode(var type: Int, var desc: String) {
    MODE1(0, "星星模式"),
    MODE2(1, "横线模式")
}