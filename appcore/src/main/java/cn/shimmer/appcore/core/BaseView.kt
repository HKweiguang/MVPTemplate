package cn.shimmer.appcore.core

import cn.shimmer.appcore.ui.LoadingLayout

interface BaseView {
    /**
     * 加载页面
     */
    fun LoadLayout(loadingLayout: LoadingLayout)
    /**
     * 显示加载中
     */
    fun startLoading()
    /**
     * 隐藏加载
     */
    fun finishLoading()

    /**
     * 数据获取失败
     */
    fun onError()
}