package com.example.bawei.homemodule.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bawei.homemodule.R;
import com.example.bawei.homemodule.bean.NewsMessageBean;

import java.util.List;

/**
 * @Author yaoxinhe
 * @CreateDate 2020/1/21 17:04
 * @Email 1151403054@qq.com
 */
public class MyMessageAdapter extends BaseQuickAdapter<NewsMessageBean, BaseViewHolder> {
    public MyMessageAdapter(int layoutResId, @Nullable List<NewsMessageBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewsMessageBean item) {
        helper.setText(R.id.tv_username_item,"用户Id为"+item.getUserid());
        helper.setText(R.id.neirong,item.getContent());
        helper.setText(R.id.time_message,item.getCommenttime());
        helper.addOnClickListener(R.id.dianzan);
    }
}
