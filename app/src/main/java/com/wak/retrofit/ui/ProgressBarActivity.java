package com.wak.retrofit.ui;

import android.util.Log;
import android.view.View;

import com.wak.retrofit.R;
import com.wak.widget_lib.progress.CircleProgressView;
import com.wak.widget_lib.progress.HorizontalProgressView;

public class ProgressBarActivity extends BaseActivity implements CircleProgressView.CircleProgressUpdateListener {

    private CircleProgressView circleProgressView, progressView_circle_main;
    private HorizontalProgressView hpv_history;

    @Override
    protected void innerView() {
        circleProgressView = findViewById(R.id.progressView_circle_small);
        progressView_circle_main = findViewById(R.id.progressView_circle_main);
        hpv_history = findViewById(R.id.hpv_history);
        circleProgressView.setProgressViewUpdateListener(this);
        progressView_circle_main.setProgressViewUpdateListener(this);
        circleProgressView.postDelayed(new Runnable() {
            @Override
            public void run() {
                circleProgressView.startProgressAnimation();
            }
        }, 500);
    }

    @Override
    protected void innerClick(View view) {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_progress;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void onCircleProgressStart(View view) {

    }

    @Override
    public void onCircleProgressUpdate(View view, float progress) {

    }

    @Override
    public void onCircleProgressFinished(View view) {
        if (view.getId() == R.id.progressView_circle_small)
            progressView_circle_main.startProgressAnimation();
        else if (view.getId() == R.id.progressView_circle_main)
            hpv_history.startProgressAnimation();
    }

    @Override
    protected void onDestroy() {
        circleProgressView.stopProgressAnimation();
        progressView_circle_main.stopProgressAnimation();
        hpv_history.stopProgressAnimation();
        circleProgressView.setProgressViewUpdateListener(null);

        super.onDestroy();
    }
}
