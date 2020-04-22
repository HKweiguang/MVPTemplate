package cn.shimmer.appcore.model

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by horsttop on 2018/4/13.
 */
@Module
class ApplicationModule(private val application: Application){

    @Provides
    @Singleton
    fun provideAppContext(): Application = application
}