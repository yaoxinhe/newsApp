package com.example.bawei.homemodule.view;

import androidx.annotation.RequiresApi;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.example.bawei.baselibrary.base.BaseActivity;
import com.example.bawei.baselibrary.net.protocol.resp.BaseRespEntity;
import com.example.bawei.homemodule.R;
import com.example.bawei.homemodule.adapter.MyViewPagerAdapter;
import com.example.bawei.homemodule.bean.TypeBean;
import com.example.bawei.homemodule.viewmodel.HomeViewModel;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.P)
public class NewsActivity extends BaseActivity<HomeViewModel, ViewDataBinding> {
    LiveData<BaseRespEntity<List<TypeBean>>> gettypw;
    TabLayout tabLayout;
    ViewPager vp;
    List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        tabLayout = findViewById(R.id.tab);
        vp = findViewById(R.id.viewpager);
        Intent intent = getIntent();
        ArrayList<TypeBean> yxh = intent.getParcelableArrayListExtra("yxh");
        if (yxh.size() == 0) {
            gettypw = mViewModel.gettype();
            gettypw.observe(this, new Observer<BaseRespEntity<List<TypeBean>>>() {
                @Override
                public void onChanged(BaseRespEntity<List<TypeBean>> typeBeanBaseRespEntity) {
                    List<TypeBean> data = typeBeanBaseRespEntity.getData();
                    initTablayout(data);
                }
            });
        } else {
            initTablayout(yxh);
        }
    }

    private void initTablayout(List<TypeBean> data) {
        for (int i = 0; i < data.size(); i++) {
            fragmentList.add(new NewsFragment(this, data.get(i).getId()));
        }
        MyViewPagerAdapter myViewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager(), data, fragmentList);
        vp.setAdapter(myViewPagerAdapter);
        tabLayout.setupWithViewPager(vp);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_news;
    }
}
