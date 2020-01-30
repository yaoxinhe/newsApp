package com.example.bawei.homemodule.view.release;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bawei.homemodule.R;
import com.lzy.imagepicker.bean.ImageItem;

import java.util.List;

public class ReleaseAdapter extends BaseQuickAdapter<ImageItem, BaseViewHolder> {

    public ReleaseAdapter(int layoutResId, @Nullable List<ImageItem> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, ImageItem item) {
        Glide.with(mContext).load(item.path).into((ImageView) helper.getView(R.id.item_release_image));
    }
}
