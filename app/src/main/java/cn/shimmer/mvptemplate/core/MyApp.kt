package cn.shimmer.mvptemplate.core

import cn.shimmer.appcore.core.BaseApp
import cn.shimmer.mvptemplate.component.DaggerNetComponent
import cn.shimmer.mvptemplate.component.NetComponent
import cn.shimmer.mvptemplate.net.ApiModel
import cn.shimmer.mvptemplate.net.HttpApi

class MyApp : BaseApp() {

    companion object {
        /**
         * 只生成一次，全局单例
         */
        @JvmStatic
        lateinit var api: HttpApi
    }

    private lateinit var netComponent: NetComponent

    override fun onCreate() {
        super.onCreate()

        initDagger()
    }

    private fun initDagger() {
        netComponent =
            DaggerNetComponent.builder().apiModel(ApiModel()).coreComponent(component).build()
        netComponent.inject(this)
        api = netComponent.httpApi()
    }
}