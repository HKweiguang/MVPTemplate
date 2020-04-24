package cn.shimmer.mvptemplate.model

import android.annotation.SuppressLint
import cn.shimmer.appcore.extension.runBack
import cn.shimmer.appcore.extension.runOnMainThread
import cn.shimmer.appcore.model.callback.OnFailCallBack
import cn.shimmer.appcore.model.callback.OnSuccessCallBack
import cn.shimmer.mvptemplate.bean.Moves
import cn.shimmer.mvptemplate.contract.MainContract
import cn.shimmer.mvptemplate.net.HttpApi
import io.reactivex.functions.Consumer

@SuppressLint("CheckResult")
class MainModel constructor(var api: HttpApi) : MainContract.Model {

    fun getMovesData(
        callback1: OnSuccessCallBack<Moves>,
        callback2: OnFailCallBack
    ) {
        api.getMoves()
            .runBack(
                Consumer {
                    callback1.onSuccessCallBack(it)
                }, Consumer {
                   callback2.onFailCallBack(it)
                }
            )
//            .runOnMainThread()
//            .subscribe(
//                {
//                    callback1.onSuccessCallBack(it)
//                },
//                {
//                    callback2.onFailCallBack(it)
//                }
//            )
    }
}