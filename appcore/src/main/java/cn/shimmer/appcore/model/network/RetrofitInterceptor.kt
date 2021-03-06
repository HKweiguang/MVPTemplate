package cn.shimmer.appcore.model.network


import android.content.Context
import android.util.Base64
import cn.shimmer.appcore.BuildConfig
import cn.shimmer.appcore.utils.NetWorkUtils
import cn.shimmer.appcore.exception.ExceptionCode
import cn.shimmer.appcore.exception.NetWorkException
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import java.io.IOException

class RetrofitInterceptor(var context: Context) : Interceptor {

    companion object {
        var token: String = ""
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        if (!NetWorkUtils.isNetworkConnected(context)) {
            throw NetWorkException(
                ExceptionCode.NETWORK_EXCEPTION.message,
                ExceptionCode.NETWORK_EXCEPTION.code
            )
        }
        var request = chain.request()



        if (token.isEmpty().not()) {
            request = request.newBuilder()
                .addHeader("Authorization",
                    token
                )
                .build()
        }
        val response = chain.proceed(request)

        when (response.code) {
            401 -> {
                throw NetWorkException(
                    ExceptionCode.ACCESS_EXCEPTION.message,
                    ExceptionCode.ACCESS_EXCEPTION.code
                )
            }
            500 -> {
                throw NetWorkException(
                    ExceptionCode.SERVER_EXCEPTION.message,
                    ExceptionCode.SERVER_EXCEPTION.code
                )
            }
        }



        return response
    }

    private fun getToken(): Request {
        val bearerToken = BuildConfig.CONSUMER_KEY +
                ":" + BuildConfig.CONSUMER_SECRET

        val base64BearerToken =
            "Basic " + Base64.encodeToString(bearerToken.toByteArray(), Base64.NO_WRAP)
        val requestBody = RequestBody.create(
            "application/x-www-form-urlencoded; charset=UTF-8".toMediaTypeOrNull(),
            "grant_type=client_credentials"
        )

        return Request.Builder()
            .url(BuildConfig.AUTH_END_POINT)
            .post(requestBody)
            .header("Authorization", base64BearerToken)
            .header("Content-Encoding", "gzip")
            .header("User-Agent", "shimmer")
            .header("Content-type", "application/x-www-form-urlencoded;charset=UTF-8")
            .build()
    }
}
