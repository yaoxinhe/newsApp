package com.example.bawei.homemodule.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;

import java.util.List;

/**
 * @Author yaoxinhe
 * @CreateDate 2020/1/17 15:11
 * @Email 1151403054@qq.com
 */
public class MyTagAdapter extends TagAdapter<String> {
    Context context;

    public MyTagAdapter(List<String> datas, Context context) {
        super(datas);
        this.context = context;
    }

    @Override
    public View getView(FlowLayout parent, int position, String s) {
        TextView textView = new TextView(context);
        textView.setText(s);
        return textView;
    }
}
