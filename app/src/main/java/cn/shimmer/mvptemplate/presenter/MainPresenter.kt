package cn.shimmer.mvptemplate.presenter

import cn.shimmer.mvptemplate.contract.MainContract
import cn.shimmer.mvptemplate.model.MainModel
import cn.shimmer.core.model.callback.OnFailCallBack
import cn.shimmer.core.model.callback.OnSuccessCallBack
import cn.shimmer.core.core.BasePresenter

class MainPresenter : BasePresenter<MainContract.View>(), MainContract.Presenter {

    val mainModel = MainModel()

    override fun login(name: String, password: String) {
        mainModel.getUserInfo(name,
            OnSuccessCallBack {
                mView?.loginSuccess(it)
            }, OnFailCallBack {

            })
    }
}