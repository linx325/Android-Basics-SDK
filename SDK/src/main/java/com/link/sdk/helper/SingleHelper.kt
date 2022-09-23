package com.link.sdk.helper

/**
 * 单例模式（示例）
 */
class SingleHelper {

    companion object {
        val instance by lazy(LazyThreadSafetyMode.NONE) {
            SingleHelper()
        }
    }

}