package cn.shimmer.appcore.extension

import android.content.Context
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import cn.shimmer.appcore.exception.NetWorkException
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers


fun Context.ofColor(@ColorRes id: Int): Int {
    return ContextCompat.getColor(this, id)
}

fun Context.dp2px(dpValue: Float): Int {
    val scale = resources.displayMetrics.density
    return (dpValue * scale + 0.5f).toInt()
}

fun Context.px2dp(pxValue: Float): Int {
    val scale = resources.displayMetrics.density
    return (pxValue / scale + 0.5f).toInt()
}

fun <T> Observable<T>.runOnMainThread(): Observable<T> {
    return this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

fun <T> Observable<T>.runBack(onNext: Consumer<in T>, onError: Consumer<Throwable>): Disposable {
    return this.runOnMainThread().subscribe(
        {
            onNext.accept(it)
        },
        {
            onError.accept(it)
        }
    )
}