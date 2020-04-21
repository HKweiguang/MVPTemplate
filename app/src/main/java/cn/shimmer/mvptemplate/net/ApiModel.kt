package cn.shimmer.mvptemplate.net

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

//@Singleton
@Module
class ApiModel {

    @Provides
    fun ofRetrofit(retrofit: Retrofit): HttpApi {
        return retrofit.create(HttpApi::class.java)
    }
}