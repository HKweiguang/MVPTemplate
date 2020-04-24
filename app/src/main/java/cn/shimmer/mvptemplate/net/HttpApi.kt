package cn.shimmer.mvptemplate.net

import cn.shimmer.mvptemplate.bean.Moves
import io.reactivex.Observable
import retrofit2.http.GET

interface HttpApi {

    @GET("/PageSubArea/Trailerlist.api")
    fun getMoves(): Observable<Moves>
}