package cn.shimmer.appcore.core

import android.app.Application
import cn.shimmer.appcore.component.CoreComponent
import cn.shimmer.appcore.component.DaggerCoreComponent
import cn.shimmer.appcore.model.ApplicationModule
import com.squareup.moshi.Moshi
import org.greenrobot.eventbus.EventBus

open class BaseApp : Application() {

    companion object {
        @JvmStatic
        private lateinit var instance: Application

        @JvmStatic
        lateinit var bus: EventBus

        @JvmStatic
        lateinit var moshi: Moshi

        @JvmStatic
        fun getInstance() = instance
    }

    protected lateinit var coreComponent: CoreComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        bus = EventBus()
        moshi = Moshi.Builder().build()

        initDagger()
    }

    private fun initDagger() {
        coreComponent = DaggerCoreComponent.builder().applicationModule(
            ApplicationModule(
                getInstance()
            )
        ).build()
    }
}