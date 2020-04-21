package cn.shimmer.mvptemplate.contract

import cn.shimmer.mvptemplate.model.form.UserFrom
import cn.shimmer.core.core.BaseView

interface MainContract {

    interface View : BaseView {
        fun loginSuccess(it: UserFrom)
    }

    interface Presenter {
        fun login(name: String, password: String)
    }
}