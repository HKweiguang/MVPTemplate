package cn.shimmer.mvptemplate.core;

import android.os.Bundle;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import cn.shimmer.appcore.core.BaseActivity;
import cn.shimmer.appcore.core.BasePresenter;
import cn.shimmer.appcore.ui.LoadingLayout;

public abstract class BaseMvpActivity<P extends BasePresenter> extends BaseActivity {

    private LoadingLayout loadingLayout;

    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroy();
    }

    @Override
    public void LoadLayout(@NotNull LoadingLayout loadingLayout) {
        this.loadingLayout = loadingLayout;
    }

    @Override
    public void startLoading() {
        loadingLayout.startLoading();
    }

    @Override
    public void finishLoading() {
        loadingLayout.completeLoading();
    }

    @Override
    public void onError() {
        loadingLayout.loadingFail();
    }
}
