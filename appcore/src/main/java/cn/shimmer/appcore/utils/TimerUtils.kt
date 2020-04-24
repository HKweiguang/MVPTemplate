package cn.shimmer.appcore.utils

import cn.shimmer.appcore.core.BaseApp
import java.util.*
import kotlin.concurrent.timerTask

object TimerUtils {

    private val timer = Timer()

    fun run(delay: Long, block: () -> Unit) {
        timer.schedule(timerTask {
            block()
        }, delay)
    }
}