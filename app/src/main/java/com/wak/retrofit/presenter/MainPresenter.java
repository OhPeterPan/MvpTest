package com.wak.retrofit.presenter;

import com.wak.retrofit.bean.ComResponse;
import com.wak.retrofit.bean.ErrorModel;
import com.wak.retrofit.callback.IResultCallback;
import com.wak.retrofit.task.LoginTask;
import com.wak.retrofit.view.IMainView;

import java.io.IOException;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class MainPresenter extends BasePresenter<IMainView, LoginTask> implements IResultCallback {

    public MainPresenter(IMainView view, LoginTask task) {
        super(view, task);
    }

    public void send() {
        setTag(0);
        task.execute(this);
    }

    public void sendError() {
        setTag(1);
        task.vExecute(this);
    }

    public void getApkTask() {
        setTag(2);
        task.downloadApkTask(this);
    }

    public void upload(RequestBody body) {
        setTag(3);
        task.uploadFile(body, this);

    }

    public void sendUserInfo(RequestBody body) {
        addDisposable(task.sendUserInfo(body, this));
    }

    @Override
    public void onSuccess(ResponseBody o) {

    }


    @Override
    public void onNext(Object o) {
        if (view == null) return;
        try {
            switch (getTag()) {
                case 0:
                    view.result(((ResponseBody) o).string());
                    break;
                case 1:
                    view.result(((ComResponse<ErrorModel>) o).datas.error);
                    break;
                case 2:
                    view.uploadApk((ResponseBody) o);
                    break;
                case 3:
                    view.loadPic(((ResponseBody) o).string());
                    break;
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
