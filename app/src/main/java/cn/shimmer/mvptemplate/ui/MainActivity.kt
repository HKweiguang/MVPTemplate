package cn.shimmer.mvptemplate.ui

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import cn.shimmer.appcore.utils.GlideUtils
import cn.shimmer.appcore.utils.ToastUtil
import cn.shimmer.mvptemplate.R
import cn.shimmer.mvptemplate.bean.Moves
import cn.shimmer.mvptemplate.bean.Trailers
import cn.shimmer.mvptemplate.contract.MainContract
import cn.shimmer.mvptemplate.core.BaseMvpActivity
import cn.shimmer.mvptemplate.presenter.MainPresenter
import cn.shimmer.mvptemplate.ui.adapter.MainMovesAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseMvpActivity<MainPresenter>(), MainContract.View {

    override fun getMovesSuccess(it: Moves) {
        trailers.clear()
        trailers.addAll(it.trailers)
        movesAdapter.notifyDataSetChanged()

        /**
         * 网络访问后使用，关闭通用加载页面
         */
        finishLoading()
    }

    override fun isStatusLight() = true

    override fun setLayoutRes() = R.layout.activity_main

    private lateinit var movesAdapter: MainMovesAdapter
    private val trailers = mutableListOf<Trailers>()

    override fun init() {
        initView()

        mPresenter = MainPresenter()
        mPresenter.attachView(this)

        main_recycler.layoutManager = LinearLayoutManager(this).apply {
            orientation = LinearLayoutManager.VERTICAL
        }
        movesAdapter = MainMovesAdapter(trailers)
        main_recycler.adapter = movesAdapter

        mPresenter.getMoves()

        /**
         * 网络访问前使用，显示通用加载页面
         */
        startLoading()
        GlideUtils.getInstance()
    }

    private fun initView() {
        toolbar.layoutParams.height += barHeight
        toolbar.setPadding(0, barHeight, 0, 0)
    }

    override fun onBackPressed() {
        AlertDialog.Builder(this).run {
            setMessage("点击确定退出")
            setPositiveButton("确定") { dialog, which ->
                ActivityCollector.finishAll()
            }
            setNegativeButton("取消") { dialog, which ->
            }
            show()
        }
        ToastUtil.showShort("111")
    }

    /**
     * 示例：在每一个 activity 后定义跳转的方法
     */
    companion object {
        fun actionStart(context: Context) {
            val intent = Intent(context, MainActivity::class.java).apply {
                putExtras(Bundle().apply {
                })
            }
            context.startActivity(intent)
        }
    }
}
