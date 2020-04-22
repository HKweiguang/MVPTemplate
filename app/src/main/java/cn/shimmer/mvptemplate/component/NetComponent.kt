package cn.shimmer.mvptemplate.component

import android.app.Application
import cn.shimmer.appcore.component.ApplicationScope
import cn.shimmer.appcore.component.CoreComponent
import cn.shimmer.mvptemplate.net.ApiModel
import cn.shimmer.mvptemplate.net.HttpApi
import dagger.Component

@ApplicationScope
@Component(dependencies = arrayOf(CoreComponent::class), modules = arrayOf(ApiModel::class))
interface NetComponent {

    fun httpApi(): HttpApi

    fun inject(application: Application)
}