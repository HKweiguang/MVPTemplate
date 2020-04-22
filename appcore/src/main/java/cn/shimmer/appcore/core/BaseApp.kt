package cn.shimmer.appcore.core

import android.app.Application
import cn.shimmer.appcore.component.CoreComponent
import cn.shimmer.appcore.component.DaggerCoreComponent
import cn.shimmer.appcore.model.ApplicationModule

open class BaseApp : Application() {

    companion object {
        @JvmStatic
        lateinit var instance: Application
    }

    protected lateinit var coreComponent: CoreComponent

    override fun onCreate() {
        super.onCreate()
        instance = this

        initDagger()
    }

    private fun initDagger() {
        coreComponent = DaggerCoreComponent.builder().applicationModule(
            ApplicationModule(
                this
            )
        ).build()
    }
}