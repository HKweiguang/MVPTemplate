package cn.shimmer.mvptemplate.model

import cn.shimmer.mvptemplate.model.form.UserFrom
import cn.shimmer.core.model.callback.OnFailCallBack
import cn.shimmer.core.model.callback.OnSuccessCallBack

class MainModel() {

    fun getUserInfo(
        name: String,
        callback1: OnSuccessCallBack<UserFrom>,
        callback2: OnFailCallBack
    ) {
        try {
            callback1.onSuccessCallBack(
                UserFrom(
                    "",
                    0
                )
            )
        } catch (t: Throwable) {
            callback2.onFailCallBack(t)
        }
    }
}