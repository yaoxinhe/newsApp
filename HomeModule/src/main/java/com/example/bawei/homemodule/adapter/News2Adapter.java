package com.example.bawei.homemodule.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bawei.homemodule.R;
import com.example.bawei.homemodule.bean.ReleaseContentRespEntity;

import java.util.Arrays;
import java.util.List;

public class News2Adapter extends BaseQuickAdapter<ReleaseContentRespEntity, BaseViewHolder> {
    public News2Adapter(int layoutResId, @Nullable List<ReleaseContentRespEntity> data) {
        super(layoutResId, data);

    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, ReleaseContentRespEntity item) {
        helper.setText(R.id.item_news_nickname, String.valueOf(item.getUserid()))
                .setText(R.id.item_news_createtime, item.getCtime())
                .setText(R.id.item_news_content, item.getContent())
                .addOnClickListener(
                        R.id.item_news_attention,R.id.item_news_like,R.id.item_news_comment

                  );

        RecyclerView imageRecyclerView = helper.getView(R.id.item_news_imagelist);
        imageRecyclerView.setLayoutManager(new GridLayoutManager(mContext,3));
        String[] imageArr = item.getImgs().split("\\|");
        imageRecyclerView.setAdapter(new ImageAdapter(R.layout.item_release, Arrays.asList(imageArr)));
    }
}
