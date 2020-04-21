package cn.shimmer.appcore.model.network

import cn.shimmer.appcore.core.BaseApp
import cn.shimmer.appcore.model.network.BigDecimalAdapter
import cn.shimmer.appcore.model.network.RetrofitInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

@Module
class NetWorkModel() {

    @Provides
    fun ofMoshi(): Moshi {
        return Moshi.Builder()
            // Add any other JsonAdapter factories.
            .add(BigDecimalAdapter)
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    fun ofMoshiConverterFactory(moshi: Moshi): MoshiConverterFactory {
        return MoshiConverterFactory.create(moshi)
    }

    @Provides
    fun ofOkHttpCache(): Cache =
        Cache(BaseApp.instance.cacheDir, 10 * 1024 * 1024)

    @Provides
    fun ofOkHttpClient(cache: Cache): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                RetrofitInterceptor(
                    BaseApp.instance
                )
            )
            .addInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
            )
            .cache(cache)
            .retryOnConnectionFailure(true)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun ofRetrofit(okHttpClient: OkHttpClient, factory: MoshiConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://127.0.0.1")
            .client(okHttpClient)
            .addConverterFactory(factory)
            .build()
    }
}