package cn.shimmer.mvptemplate.net

import cn.shimmer.mvptemplate.bean.Moves
import retrofit2.Call
import retrofit2.http.GET

interface HttpApi {

    @GET("/PageSubArea/Trailerlist.api")
    fun getMoves(): Call<Moves>
}