package cn.shimmer.appcore.core

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Process

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setLayout())
        supportActionBar?.hide()
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
    }

    /**
     * 设置 layout 视图
     */
    abstract fun setLayout(): Int

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
