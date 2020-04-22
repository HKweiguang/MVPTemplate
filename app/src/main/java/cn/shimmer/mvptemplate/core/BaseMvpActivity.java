package cn.shimmer.mvptemplate.core;

import android.os.Bundle;

import org.jetbrains.annotations.Nullable;

import cn.shimmer.appcore.core.BaseActivity;
import cn.shimmer.appcore.core.BasePresenter;
import cn.shimmer.appcore.core.BaseView;

public abstract class BaseMvpActivity<P extends BasePresenter> extends BaseActivity implements BaseView {

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
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onError() {

    }
}
