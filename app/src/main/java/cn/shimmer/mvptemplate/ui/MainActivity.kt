package cn.shimmer.mvptemplate.ui

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import cn.shimmer.mvptemplate.R
import cn.shimmer.mvptemplate.contract.MainContract
import cn.shimmer.mvptemplate.model.form.UserInfo
import cn.shimmer.mvptemplate.presenter.MainPresenter
import cn.shimmer.appcore.core.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseMvpActivity<MainPresenter>(), MainContract.View {

    override fun loginSuccess(it: UserInfo) {
        text.text = it.name
    }

    override fun setLayout() = R.layout.activity_main

    override fun init() {
        mPresenter = MainPresenter()
        mPresenter.attachView(this)

        mPresenter.login(name = "test", password = "test")
    }

    override fun onBackPressed() {
        AlertDialog.Builder(this).run {
            setMessage("点击确定退出")
            setPositiveButton("确定"){
                dialog, which ->  super.onBackPressed()
            }
            setNegativeButton("取消") {
                dialog, which ->
            }
            show()
        }
        Toast.makeText(this, "111", Toast.LENGTH_SHORT).show()

    }

    /**
     * 示例：在每一个 activity 后定义跳转的方法
     */
    companion object {
        fun actionStart(context: Context, data1: String) {
            val intent = Intent(context, MainActivity::class.java).apply {
                putExtras(Bundle().apply {
                    putString("data1", data1)
                })
            }
            context.startActivity(intent)
        }
    }
}
