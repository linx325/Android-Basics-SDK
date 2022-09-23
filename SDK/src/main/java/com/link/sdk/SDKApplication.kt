package com.link.sdk

import android.app.Application
import com.elvishew.xlog.LogConfiguration
import com.elvishew.xlog.LogLevel
import com.elvishew.xlog.XLog
import com.scwang.smartrefresh.header.WaterDropHeader
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.footer.BallPulseFooter

class SDKApplication : Application() {

    companion object {
        lateinit var instance: SDKApplication

        //SmartRefreshLayout上拉加载下拉刷新构建器初始化
        init {
            //设置全局的Header构建器
            SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
                //全局设置主题颜色
                layout.setPrimaryColorsId(R.color._1A337C, android.R.color.white)
                //new ClassicsHeader(context).setTimeFormat(new DynamicTimeFormat("更新于 %s"));
                WaterDropHeader(context)
            }
            //设置全局的Footer构建器
            SmartRefreshLayout.setDefaultRefreshFooterCreator { context, layout ->
                //return new ClassicsFooter(context).setDrawableSize(20);
                BallPulseFooter(context)
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        initXLog()
    }

    /**
     * 初始化XLog日志
     */
    private fun initXLog() {
        val logConfig = LogConfiguration.Builder()
            .logLevel(LogLevel.ALL)
            .enableStackTrace(1)
            .addInterceptor {
                //......
                it
            }
            .build()
        XLog.init(logConfig)
    }

}