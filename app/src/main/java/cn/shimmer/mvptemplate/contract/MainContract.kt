package cn.shimmer.mvptemplate.contract

import cn.shimmer.appcore.core.BaseModel
import cn.shimmer.appcore.core.BaseView
import cn.shimmer.mvptemplate.bean.Moves

interface MainContract {

    interface Model : BaseModel

    interface View : BaseView {
        fun getMovesSuccess(it: Moves)
    }

    interface Presenter
}