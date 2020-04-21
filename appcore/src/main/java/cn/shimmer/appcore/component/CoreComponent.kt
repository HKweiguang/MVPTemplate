package cn.shimmer.appcore.component

import cn.shimmer.appcore.model.network.NetWorkModel
import com.squareup.moshi.Moshi
import dagger.Component
import retrofit2.Retrofit

@Component(modules = arrayOf(NetWorkModel::class))
interface CoreComponent {

    fun moshi(): Moshi

    fun retrofit(): Retrofit
}