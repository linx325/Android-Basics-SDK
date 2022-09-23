package com.link.sdk.helper

/**
 * 用来临时保存/获取 App 中的一些共享数据
 */
object DataHelper {

    private val dataMap = mutableMapOf<String, Any?>()

    private fun <T> putData(key: String, data: T) {
        dataMap[key] = data
    }

    private fun <T> getData(key: String): T? {
        return dataMap[key] as T?
    }

    fun removeData(key: String) {
        dataMap.remove(key)
    }

    /**
     * 存零时共享数据
     */
    fun putShared(key: String, value: Any) {
        putData(key, value)
    }

    /**
     * 取零时共享数据
     */
    fun <T> getShared(key: String): T? {
        return getData(key)
    }
}