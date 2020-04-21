package cn.shimmer.mvptemplate.core

import cn.shimmer.appcore.core.BaseApp
import cn.shimmer.mvptemplate.component.DaggerNetComponent
import cn.shimmer.mvptemplate.component.NetComponent
import cn.shimmer.mvptemplate.net.ApiModel

class MyApp : BaseApp() {

    companion object {
        @JvmStatic
        lateinit var netComponent: NetComponent
    }

    override fun onCreate() {
        super.onCreate()

        initDagger()
    }

    private fun initDagger() {
        netComponent =
            DaggerNetComponent.builder().apiModel(ApiModel()).coreComponent(component).build()
    }
}