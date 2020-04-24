package cn.shimmer.appcore.core

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Process
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import cn.shimmer.appcore.ui.LoadingLayout


abstract class BaseActivity() : AppCompatActivity(), BaseView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSystemStatusBar()
        setContentView(setLayoutView())
        ActivityCollector.addActivity(
            this
        )

        init()
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityCollector.removeActivity(
            this
        )
        // evenbus 注销
        if (BaseApp.bus.isRegistered(this)) {
            BaseApp.bus.unregister(this)
        }
    }

    /**
     * 设置状态栏字体
     */
    abstract fun isStatusLight(): Boolean

    /**
     * 设置状态栏
     */
    private fun setSystemStatusBar() {
        getStatusBarHeight()
        if (isStatusLight()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
        }
        window.statusBarColor = Color.TRANSPARENT
    }

    /**
     * 设置 layout 视图
     */
    abstract fun setLayoutRes(): Int

    private fun setLayoutView(): View {
        val loadingLayout = LoadingLayout(this, setLayoutRes())
        LoadLayout(loadingLayout)
        return loadingLayout
    }

    var barHeight = 0

    /**
     * 获取状态栏高度
     */
    private fun getStatusBarHeight(): Int {
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            barHeight = resources.getDimensionPixelOffset(resourceId)
        }
        return barHeight
    }

    /**
     * 通用初始化方法
     */
    abstract fun init()

    object ActivityCollector {
        private val activites = arrayListOf<Activity>()

        fun addActivity(activity: Activity) {
            activites.add(activity)
        }

        fun removeActivity(activity: Activity) {
            activites.remove(activity)
        }

        fun finishAll() {
            for (activity in activites) {
                if (!activity.isFinishing) {
                    activity.finish()
                }
            }
            activites.clear()
            Process.killProcess(Process.myPid())
        }
    }
}
