package cn.shimmer.appcore.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.RelativeLayout
import android.widget.TextView
import cn.shimmer.appcore.R

class LoadingLayout constructor(context: Context) : FrameLayout(context) {

    private lateinit var mRlLoadingView: RelativeLayout
    private lateinit var tv_loading: TextView

    constructor(context: Context, res: Int) : this(context) {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(res, this)
        val rootView = inflater.inflate(R.layout.loading_layout, this)
        mRlLoadingView = rootView.findViewById<RelativeLayout>(R.id.rl_loading)
        tv_loading = rootView.findViewById<TextView>(R.id.tv_loading)
    }

    constructor(context: Context, view: View) : this(context) {
        addView(view)
        val inflater = LayoutInflater.from(context)
        val rootView = inflater.inflate(R.layout.loading_layout, this)
        mRlLoadingView = rootView.findViewById<RelativeLayout>(R.id.rl_loading)
        tv_loading = rootView.findViewById<TextView>(R.id.tv_loading)
    }

    fun startLoading() {
        tv_loading.text = "正在加载中..."
        mRlLoadingView.visibility = View.VISIBLE

    }

    fun completeLoading() {
        tv_loading.text = "加载完成!"
        mRlLoadingView.visibility = View.GONE
    }

    fun loadingFail() {
        tv_loading.text = "加载失败!"
        mRlLoadingView.visibility = View.VISIBLE
    }
}