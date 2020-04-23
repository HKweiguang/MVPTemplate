package cn.shimmer.appcore.core

interface BaseView {
    /**
     * 显示加载中
     */
    //    void showLoading(res: Integer);
    /**
     * 隐藏加载
     */
    fun hideLoading()

    /**
     * 数据获取失败
     */
    fun onError()
}