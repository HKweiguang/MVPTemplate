package cn.shimmer.appcore.exception

/**
 * Created by horsttop on 2017/12/24.
 */

class QuitException(message: String, var code:Int = 0) : RuntimeException(message)

enum class ExceptionCode(var message: String,var code: Int = 0){

    NETWORK_EXCEPTION("网络异常",-1),

    ACCESS_EXCEPTION("授权异常",401),

    SERVER_EXCEPTION("服务异常",500),

    DEFAULT_EXCEPTION("",0)

}
