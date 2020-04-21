package cn.shimmer.mvptemplate.model

import cn.shimmer.mvptemplate.model.form.UserInfo
import cn.shimmer.appcore.model.callback.OnFailCallBack
import cn.shimmer.appcore.model.callback.OnSuccessCallBack
import cn.shimmer.mvptemplate.contract.MainContract
import cn.shimmer.mvptemplate.net.HttpApi

class MainModel constructor(var api: HttpApi): MainContract.Model {

    fun getUserInfo(
        name: String,
        callback1: OnSuccessCallBack<UserInfo>,
        callback2: OnFailCallBack
    ) {
        try {
            callback1.onSuccessCallBack(
                UserInfo(
                    "",
                    0
                )
            )
        } catch (t: Throwable) {
            callback2.onFailCallBack(t)
        }
    }
}