package com.wak.retrofit.ui;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.wak.retrofit.R;
import com.wak.retrofit.presenter.BasePresenter;

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements IBase, View.OnClickListener {
    T presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initPresenter();
        initView();
        initListener();
        initData();
    }

    protected void setPresenter(T presenter) {
        this.presenter = presenter;
    }

    public T getPresenter() {
        return presenter;
    }

    protected void initView() {
        View back = findViewById(R.id.back);
        if (back != null)
            back.setOnClickListener(this);
        innerView();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
        }
        innerClick(view);
    }

    protected abstract void innerView();

    protected abstract void innerClick(View view);

    protected abstract void initListener();

    protected abstract void initData();

    @Override
    public void onEnd() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) presenter.onDestroy();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void onFail(Throwable throwable) {

    }


}
