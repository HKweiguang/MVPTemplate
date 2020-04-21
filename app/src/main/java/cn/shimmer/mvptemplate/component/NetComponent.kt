package cn.shimmer.mvptemplate.component

import cn.shimmer.appcore.component.CoreComponent
import cn.shimmer.mvptemplate.net.ApiModel
import cn.shimmer.mvptemplate.net.HttpApi
import dagger.Component

@Component(dependencies = arrayOf(CoreComponent::class), modules = arrayOf(ApiModel::class))
interface NetComponent {

    fun httpApi(): HttpApi
}