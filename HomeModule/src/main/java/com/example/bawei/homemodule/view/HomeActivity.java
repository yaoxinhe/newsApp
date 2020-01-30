package com.example.bawei.homemodule.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;

import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.bawei.baselibrary.MyInter.YxhInjectView;
import com.example.bawei.baselibrary.base.BaseActivity;
import com.example.bawei.baselibrary.common.StartIntent;
import com.example.bawei.baselibrary.net.protocol.resp.BaseRespEntity;
import com.example.bawei.homemodule.R;
import com.example.bawei.homemodule.bean.TypeBean;
import com.example.bawei.homemodule.adapter.SelectAdapter;
import com.example.bawei.homemodule.viewmodel.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity<HomeViewModel, ViewDataBinding> {
    LiveData<BaseRespEntity<List<TypeBean>>> gettypw;
    List<TypeBean> data,data2;
    private RecyclerView select_re;
    private Button select_next;
    private SelectAdapter selectAdapter;
    @Override
    protected void initEvent() {
        gettypw = mViewModel.gettype();
        gettypw.observe(this, new Observer<BaseRespEntity<List<TypeBean>>>() {
            @Override
            public void onChanged(BaseRespEntity<List<TypeBean>> typeBeanBaseRespEntity) {
                data = typeBeanBaseRespEntity.getData();
                init_item();
            }
        });
        select_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, NewsActivity.class);
                intent.putParcelableArrayListExtra("yxh", (ArrayList<? extends Parcelable>) data2);
                startActivity(intent);
                HomeActivity.this.finish();
            }
        });
    }

    private void init_item() {
        select_re.setLayoutManager(new GridLayoutManager(HomeActivity.this,3));
        selectAdapter=new SelectAdapter(R.layout.select_adapter_item,data);
        select_re.setAdapter(selectAdapter);
        selectAdapter.notifyDataSetChanged();
        selectAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId()==R.id.select_item_text) {
                    view.setBackgroundColor(Color.YELLOW);
                    data2.add(data.get(position));
                }
            }
        });


    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        select_re = findViewById(R.id.select_re);
        select_next = findViewById(R.id.select_next);
        data2=new ArrayList<>();


    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }


}
