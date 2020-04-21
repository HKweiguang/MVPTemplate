package cn.shimmer.mvptemplate.contract

import cn.shimmer.appcore.core.BaseModel
import cn.shimmer.appcore.core.BasePresenter
import cn.shimmer.mvptemplate.model.form.UserInfo
import cn.shimmer.appcore.core.BaseView

interface MainContract {

    interface Model : BaseModel

    interface View : BaseView {
        fun loginSuccess(it: UserInfo)
    }

    interface Presenter
}