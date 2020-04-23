package cn.shimmer.mvptemplate.model

import cn.shimmer.appcore.model.callback.OnFailCallBack
import cn.shimmer.appcore.model.callback.OnSuccessCallBack
import cn.shimmer.mvptemplate.bean.Moves
import cn.shimmer.mvptemplate.contract.MainContract
import cn.shimmer.mvptemplate.net.HttpApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainModel constructor(var api: HttpApi) : MainContract.Model {

    fun getMovesData(
        callback1: OnSuccessCallBack<Moves>,
        callback2: OnFailCallBack
    ) {
        val call = api.getMoves()
        call.enqueue(object : Callback<Moves> {
            override fun onFailure(call: Call<Moves>, t: Throwable) {
                callback2.onFailCallBack(t)
            }

            override fun onResponse(call: Call<Moves>, response: Response<Moves>) {
                callback1.onSuccessCallBack(response.body())
            }

        })
    }
}