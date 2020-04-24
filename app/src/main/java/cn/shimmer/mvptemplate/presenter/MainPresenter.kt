package cn.shimmer.mvptemplate.presenter

import cn.shimmer.appcore.core.BasePresenter
import cn.shimmer.appcore.model.callback.OnFailCallBack
import cn.shimmer.appcore.model.callback.OnSuccessCallBack
import cn.shimmer.appcore.utils.ToastUtil
import cn.shimmer.mvptemplate.contract.MainContract
import cn.shimmer.mvptemplate.core.MyApp
import cn.shimmer.mvptemplate.model.MainModel

class MainPresenter : BasePresenter<MainContract.View>(), MainContract.Presenter {

    private val mainModel = MainModel(MyApp.api)

    fun getMoves() {
        mainModel.getMovesData(
            OnSuccessCallBack {
                mView.getMovesSuccess(it)
            }, OnFailCallBack {
                mView.onError()
                ToastUtil.showShort(it.message!!)
                it.printStackTrace()
            }
        )
    }
}