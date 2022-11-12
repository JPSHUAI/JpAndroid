package com.jp.androidpro

import android.app.Application
import com.drake.brv.utils.BRV
import com.drake.net.BuildConfig
import com.drake.net.NetConfig
import com.drake.net.interceptor.LogRecordInterceptor
import com.drake.net.okhttp.setConverter
import com.drake.net.okhttp.setDebug
import com.drake.net.sample.constants.Api
import com.drake.statelayout.StateConfig
import com.jp.androidpro.data.net.SerializationConverter
import okhttp3.Cache
import java.util.concurrent.TimeUnit

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        /**
         *  推荐在Application中进行全局配置缺省页, 当然同样每个页面可以单独指定缺省页.
         *  具体查看 https://github.com/liangjingkanji/StateLayout
         */
        StateConfig.apply {
            emptyLayout = R.layout.layout_empty
            errorLayout = R.layout.layout_error
            loadingLayout = R.layout.layout_loading

            setRetryIds(R.id.msg)

            onLoading {
                // 此生命周期可以拿到LoadingLayout创建的视图对象, 可以进行动画设置或点击事件.
            }
        }

        NetConfig.initialize(Api.HOST, this) {
            // 超时设置
            connectTimeout(30, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
            writeTimeout(30, TimeUnit.SECONDS)

            // 本框架支持Http缓存协议和强制缓存模式
            // 缓存设置, 当超过maxSize最大值会根据最近最少使用算法清除缓存来限制缓存大小
            cache(Cache(cacheDir, 1024 * 1024 * 128))
            // LogCat是否输出异常日志, 异常日志可以快速定位网络请求错误
            setDebug(BuildConfig.DEBUG)
            // AndroidStudio OkHttp Profiler 插件输出网络日志
            addInterceptor(LogRecordInterceptor(BuildConfig.DEBUG))

            // 数据转换器
            setConverter(SerializationConverter())

        }

        BRV.modelId = BR.m

    }

}