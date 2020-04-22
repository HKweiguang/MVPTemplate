package cn.shimmer.appcore.core

import android.app.Application
import cn.shimmer.appcore.component.CoreComponent
import cn.shimmer.appcore.component.DaggerCoreComponent
import cn.shimmer.appcore.model.ApplicationModule
import cn.shimmer.appcore.model.network.NetWorkModel

open class BaseApp : Application() {

    companion object {
        @JvmStatic
        lateinit var instance: Application

        @JvmStatic
        lateinit var component: CoreComponent
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        initDagger()
    }

    private fun initDagger() {
        component = DaggerCoreComponent.builder().applicationModule(
            ApplicationModule(
                this
            )
        ).build()
    }
}