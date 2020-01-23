package com.example.bawei.homemodule.adapter;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bawei.homemodule.R;
import com.example.bawei.homemodule.bean.NewsBean;

import java.util.List;

/**
 * @Author yaoxinhe
 * @CreateDate 2020/1/17 17:21
 * @Email 1151403054@qq.com
 */
public class NewsAdapter extends BaseQuickAdapter<NewsBean, BaseViewHolder> {
    public NewsAdapter(int layoutResId, @Nullable List<NewsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewsBean item) {
        ImageView view = (ImageView) helper.getView(R.id.item_news_image);
        if(item.getIstop()=="1"){
            helper.setText(R.id.item_news_title,item.getTitle());
            view.setVisibility(View.GONE);
            helper.setText(R.id.hot,"置顶");
        }else{
            helper.setText(R.id.item_news_title,item.getTitle());
            view.setVisibility(View.VISIBLE);
            Glide.with(mContext).load(item.getMainimgurl()).into(view);
            helper.setText(R.id.hot,"热");

        }
    }
}
