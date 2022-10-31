package com.link.sdk.helper

import android.app.Application
import com.elvishew.xlog.XLog
import com.link.sdk.BuildConfig
import com.umeng.analytics.MobclickAgent
import com.umeng.commonsdk.UMConfigure
import com.umeng.message.PushAgent
import com.umeng.message.api.UPushRegisterCallback
import com.umeng.socialize.PlatformConfig
import com.youma.qfy.app.main.um.MyUmengMessageHandler
import com.youma.qfy.app.main.um.MyUmengNotificationClickHandler
import org.android.agoo.huawei.HuaWeiRegister
import org.android.agoo.xiaomi.MiPushRegistar

/**
 * 友盟初始化帮助类
 */
class UmSdkHelper {

    companion object {
        val instance by lazy(LazyThreadSafetyMode.NONE) {
            UmSdkHelper()
        }
    }

    private var platConfig: PlatConfig = PlatConfig()

    private lateinit var application: Application
    private var umAppKey: String = ""
    private var umSecretKey: String = ""

    fun init(application: Application, platConfig: PlatConfig): UmSdkHelper {
        this.application = application
        this.platConfig = platConfig
        this.umAppKey = platConfig.umAppKey
        this.umSecretKey = platConfig.umSecretKey
        return this
    }

    /**
     * 友盟预初始化
     */
    fun preInit(observer: ((umToken: String) -> Unit)? = null) {
        // 在此处调用基础组件包提供的初始化函数 相应信息可在应用管理 -> 应用信息 中找到 http://message.umeng.com/list/apps
        // 参数三：渠道名称；
        // 参数四：设备类型，必须参数，传参数为UMConfigure.DEVICE_TYPE_PHONE则表示手机；传参数为UMConfigure.DEVICE_TYPE_BOX则表示盒子；默认为手机；
        // 参数五：Push推送业务的secret 填充Umeng Message Secret对应信息（需替换）
        setPlatformConfigAppKeys()
        UMConfigure.setLogEnabled(BuildConfig.DEBUG)
        MiPushRegistar.register(application, "2882303761518449313", "5441844923313")
        HuaWeiRegister.register(application)
        UMConfigure.preInit(application, umAppKey, "Umeng")

        // 选用AUTO页面采集模式
        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO)

        // 当 AndroidManifest.xml 中 package值和 appId (应用包名) 不一致时，需要在初始化前设置资源包名
        PushAgent.getInstance(application).resourcePackageName = platConfig.appPackageName

        //获取消息推送代理示例
        val mPushAgent = PushAgent.getInstance(application)

        //注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(object : UPushRegisterCallback {
            override fun onSuccess(deviceToken: String) {
                //注册成功会返回deviceToken deviceToken是推送消息的唯一标志
                XLog.d("获取友盟 deviceToken 成功：$deviceToken")
                observer?.invoke(deviceToken)
            }

            override fun onFailure(errCode: String, errDesc: String) {
                XLog.d("注册失败 code:$errCode, desc:$errDesc")
            }
        })

        mPushAgent.onAppStart()

        mPushAgent.notificationClickHandler = MyUmengNotificationClickHandler()

        mPushAgent.displayNotificationNumber = 0

        mPushAgent.messageHandler = MyUmengMessageHandler(application)
    }

    /**
     * 友盟初始化
     */
    fun initUM() {
        UMConfigure.init(application, umAppKey, "Umeng", UMConfigure.DEVICE_TYPE_PHONE, umSecretKey)
    }

    private fun setPlatformConfigAppKeys() {
        PlatformConfig.setWeixin(platConfig.wxAppKey, platConfig.wxSecretKey)
        PlatformConfig.setWXFileProvider("\${BuildConfig.APPLICATION_ID}.fileprovider")

        // QQ设置
        PlatformConfig.setQQZone(platConfig.qqAppKey, platConfig.qqSecretKey)
        PlatformConfig.setQQFileProvider("\${BuildConfig.APPLICATION_ID}.fileprovider")

        // 企业微信设置
        PlatformConfig.setWXWork(
            platConfig.wxWorkAppKey,
            platConfig.wxWorkSecretKey,
            "1000002",
            "wwauthac6ffb259ff6f66a000002"
        )
        PlatformConfig.setWXWorkFileProvider("\${BuildConfig.APPLICATION_ID}.fileprovider")

        // 支付宝设置
        PlatformConfig.setAlipay(platConfig.aliPayAppKey)
    }

}

/**
 * 配置
 */
data class PlatConfig(
    var appPackageName: String = "com.youma.qfy",
    var xiaomiId: String = "2882303761518449313",
    var xiaomiKey: String = "5441844923313",
    var wxAppKey: String = "",
    var wxSecretKey: String = "",
    var qqAppKey: String = "",
    var qqSecretKey: String = "",
    var wxWorkAppKey: String = "",
    var wxWorkSecretKey: String = "",
    var aliPayAppKey: String = "",
    var umAppKey: String = "",
    var umSecretKey: String = ""
)
