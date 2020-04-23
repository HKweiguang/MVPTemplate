package cn.shimmer.appcore.core

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.os.Process
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import cn.shimmer.appcore.ui.LoadingLayout


abstract class BaseActivity() : AppCompatActivity(), BaseView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSystemStatusBar()
        window.statusBarColor = Color.TRANSPARENT
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
    abstract fun setSystemStatusBar()

    /**
     * 设置 layout 视图
     */
    abstract fun setLayoutRes(): Int

    private fun setLayoutView(): View {
        val loadingLayout = LoadingLayout(this, setLayoutRes())
        LoadLayout(loadingLayout)
        return loadingLayout
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
