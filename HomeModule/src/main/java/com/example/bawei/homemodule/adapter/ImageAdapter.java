package com.example.bawei.homemodule.adapter;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bawei.homemodule.R;

import java.util.List;

public class ImageAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public ImageAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {
        Glide.with(mContext)
                .load(item)
                .into((ImageView) helper.getView(R.id.item_release_image));
    }
}
