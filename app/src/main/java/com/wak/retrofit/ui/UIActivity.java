package com.wak.retrofit.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.GridSpanSizeLookup;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.wak.retrofit.R;
import com.wak.widget_lib.utils.XUI;

import java.util.Arrays;
import java.util.List;

public class UIActivity extends BaseActivity {

    private RecyclerView rv;
    private String[] list = XUI.getContext().getResources().getStringArray(R.array.item);
    private BaseQuickAdapter<String, BaseViewHolder> adapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_adapter, Arrays.asList(list)) {
        @Override
        protected void convert(BaseViewHolder holder, final String s) {
            holder.setText(R.id.textAdapter, s);
            holder.getView(R.id.textAdapter).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (s) {

                        case "进度条":
                            startActivity(new Intent(UIActivity.this,ProgressBarActivity.class));
                            break;
                        case "图片":
                            break;
                        case "Dialog":
                            break;
                        case "PopupWindow":
                            startActivity(new Intent(UIActivity.this,PopupWindowActivity.class));
                            break;

                    }
                }
            });
        }
    };

    @Override
    protected void innerView() {
        rv = findViewById(R.id.recyclerView);
        rv.setAdapter(adapter);

     /*
     为每个item设置所占的列数
     adapter.setGridSpanSizeLookup(new GridSpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int viewType, int position) {
                return 0;
            }
        });*/
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
        return R.layout.activity_ui_reveal;
    }

    @Override
    public void initPresenter() {

    }

}
