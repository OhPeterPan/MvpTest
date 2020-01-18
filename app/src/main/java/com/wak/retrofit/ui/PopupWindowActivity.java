package com.wak.retrofit.ui;

import android.graphics.Color;
import android.view.View;
import android.widget.Toast;

import com.shuyu.textutillib.RichTextBuilder;
import com.shuyu.textutillib.RichTextView;
import com.shuyu.textutillib.listener.SpanAtUserCallBack;
import com.shuyu.textutillib.listener.SpanTopicCallBack;
import com.shuyu.textutillib.listener.SpanUrlCallBack;
import com.shuyu.textutillib.model.TopicModel;
import com.shuyu.textutillib.model.UserModel;
import com.wak.retrofit.R;

public class PopupWindowActivity extends BaseActivity {

    private RichTextView richTextView;


    @Override
    protected void innerView() {
        richTextView = (RichTextView) findViewById(R.id.rich_text_2);


        //配置TextView显示文本
        RichTextBuilder richTextBuilder = new RichTextBuilder(this);
    /*    richTextBuilder.setContent("")
                .setAtColor(Color.RED)
                .setLinkColor(Color.BLUE)
                .setTopicColor(Color.YELLOW)
                .setListUser(nameList)
                .setListTopic(topicModels)
                .setTextView(richTextView)
                .setSpanAtUserCallBack(spanAtUserCallBack)
                .setSpanUrlCallBack(spanUrlCallBack)
                .setSpanTopicCallBack(spanTopicCallBack)
                .build();*/

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
        return R.layout.activity_popup_window;
    }

    @Override
    public void initPresenter() {

    }
}
