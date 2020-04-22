package cn.shimmer.appcore.component

import android.app.Application
import cn.shimmer.appcore.model.ApplicationModule
import cn.shimmer.appcore.model.network.NetWorkModel
import com.squareup.moshi.Moshi
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class, NetWorkModel::class))
interface CoreComponent {

    fun application(): Application

    fun moshi(): Moshi

    fun retrofit(): Retrofit
}