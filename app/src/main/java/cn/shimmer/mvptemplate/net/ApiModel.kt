package cn.shimmer.mvptemplate.net

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApiModel {

    @Provides
    fun provideHttpApi(retrofit: Retrofit): HttpApi {
        return retrofit.create(HttpApi::class.java)
    }
}