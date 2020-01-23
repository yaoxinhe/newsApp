package com.example.bawei.homemodule.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bawei.homemodule.R;
import com.example.bawei.homemodule.bean.TypeBean;

import java.util.List;


/**
 * @author fengchen
 * @date 2020/1/12.
 * @description：
 */
public class SelectAdapter extends BaseQuickAdapter<TypeBean, BaseViewHolder> {
    public SelectAdapter(int layoutResId, @Nullable List<TypeBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TypeBean item) {
        helper.setText(R.id.select_item_text,item.getTypename())
                .addOnClickListener(R.id.select_item_text);
    }
}
