package com.wak.retrofit;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;

import android.view.View;
import android.widget.TextView;

import com.luck.picture.lib.PictureSelectionModel;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.wak.retrofit.file.DownLoadListener;
import com.wak.retrofit.file.FileAsyncTask;
import com.wak.retrofit.presenter.MainPresenter;

import com.wak.retrofit.task.LoginTask;
import com.wak.retrofit.ui.BaseActivity;
import com.wak.retrofit.view.IMainView;
import com.wak.retrofit.widget.button.ButtonView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Multipart;

/**
 * 下载数据的时候一定先给一个权限，要不然无法创建文件
 */
public class MainActivity extends BaseActivity<MainPresenter> implements IMainView {

    private TextView textView;
    private AsyncTask<ResponseBody, Integer, File> asyncTask;
    private List<LocalMedia> mSelectList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initPresenter() {
        LoginTask loginTask = new LoginTask();
        MainPresenter presenter = new MainPresenter(this, loginTask);
        setPresenter(presenter);
    }

    @Override
    protected void innerView() {
        ButtonView buttonView1 = findViewById(R.id.buttonView1);
        ButtonView buttonView2 = findViewById(R.id.buttonView2);
        ButtonView buttonView3 = findViewById(R.id.buttonView3);
        ButtonView buttonView4 = findViewById(R.id.buttonView4);
        ButtonView buttonView5 = findViewById(R.id.buttonView5);
        textView = findViewById(R.id.textView);
        buttonView1.setOnClickListener(this);
        buttonView2.setOnClickListener(this);
        buttonView3.setOnClickListener(this);
        buttonView4.setOnClickListener(this);
        buttonView5.setOnClickListener(this);
        // MultipartBody.Part.createFormData()
/*        RequestBody.create(MediaType.parse(""),);
        MultipartBody.create(MediaType.parse("multipart/form-data"),new File(""));*/
    }

    @Override
    protected void innerClick(View view) {
        switch (view.getId()) {
            case R.id.buttonView1:
                getPresenter().send();
                break;
            case R.id.buttonView2:
                getPresenter().sendError();
                break;
            case R.id.buttonView3:
                getPresenter().cancel();
                break;
            case R.id.buttonView4:
                getPresenter().getApkTask();
                break;
            case R.id.buttonView5:
                //先去选择图片
                getPictureSelector().selectionMedia(mSelectList)
                        .maxSelectNum(1)
                        .selectionMode(PictureConfig.SINGLE)
                        .forResult(PictureConfig.CHOOSE_REQUEST);
                break;
        }
    }

    public PictureSelectionModel getPictureSelector() {
        return PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .maxSelectNum(8)
                .minSelectNum(1)
                .selectionMode(PictureConfig.MULTIPLE)
                .previewImage(true)
                .isCamera(true)
                .enableCrop(false)
                .compress(true)
                .previewEggs(true);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择
                    mSelectList = PictureSelector.obtainMultipleResult(data);
                    LocalMedia localMedia = mSelectList.get(0);
                    File file = new File(localMedia.getPath());
                    //System.out.println(file.getName());
                    RequestBody body = RequestBody.create(MediaType.parse("image/*"), new File(localMedia.getPath()));
                    MultipartBody multipartBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                            .addFormDataPart("key", "47cb7d481546b3b081de20be00acd5c0")
                            .addFormDataPart("shop_id", "1")
                            .addFormDataPart("img_state", "avatar")
                            .addFormDataPart("image", file.getName(), body)
                            .build();

                    getPresenter().upload(multipartBody);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void result(String json) {
        System.out.println(json);
    }

    @Override
    public void loadPic(String result) {
        System.out.println(result);
    }

    @Override
    public void uploadApk(ResponseBody body) {

        asyncTask = new FileAsyncTask(this, new DownLoadListener() {
            @Override
            public void Success(File file) {
                //下载成功

            }

            @Override
            public void progress(int progress) {
                textView.setText(String.format("当前进度%d:", progress));
            }
        }).execute(body);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        asyncTask.cancel(true);
    }
}
