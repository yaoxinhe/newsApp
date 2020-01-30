package com.example.bawei.homemodule.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.bawei.baselibrary.base.BaseActivity;
import com.example.bawei.baselibrary.net.protocol.resp.BaseRespEntity;
import com.example.bawei.homemodule.R;
import com.example.bawei.homemodule.adapter.News2Adapter;
import com.example.bawei.homemodule.bean.ReleaseContentRespEntity;
import com.example.bawei.homemodule.viewmodel.HomeViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class CircleActivity extends BaseActivity<HomeViewModel, ViewDataBinding> {
    int guznzhu=0;
    int dianzan=0;
    private Button btAdd;
    private List<ReleaseContentRespEntity> list;
    private News2Adapter adapter;
    private LiveData<BaseRespEntity<List<ReleaseContentRespEntity>>> headlinesForUserid;
    private RecyclerView news_home_recyclerview;

    @Override
    protected void initEvent() {
        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            int id = view.getId();
            if (id == R.id.item_news_attention) {
                guznzhu++;
                if(guznzhu%2!=0){
                    //关注
                    Button button = (Button) view;
                    button.setText("已关注");
                }else {
                    Button button = (Button) view;
                    button.setText("关注");
                }

            } else if (id == R.id.item_news_like) {
                dianzan++;
                if(dianzan%2!=0){
                    ImageView imageView= view.findViewById(R.id.item_news_like);
                    imageView.setImageResource(R.drawable.dianzan);
                }else{
                    ImageView imageView= view.findViewById(R.id.item_news_like);
                    imageView.setImageResource(R.mipmap.like);
                }
            } else if (id == R.id.item_news_comment) {

            }
        });

    }

    @Override
    protected void initData() {

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences yxh = getSharedPreferences("yxh", 0);
        int userid = yxh.getInt("userid", 0);
        headlinesForUserid = mViewModel.getHeadlinesForUserid(userid);
        headlinesForUserid.observe(CircleActivity.this, listBaseEntity -> {
                list.clear();
                list.addAll(listBaseEntity.getData());
                Collections.reverse(list);
                adapter.notifyDataSetChanged();
        });
    }


    @Override
    protected void initView(Bundle savedInstanceState) {
        btAdd = findViewById(R.id.addNews);
        news_home_recyclerview=findViewById(R.id.news_home_recyclerview);
        list = new ArrayList<>();
        adapter = new News2Adapter(R.layout.item_news, list);
        news_home_recyclerview.setLayoutManager(new LinearLayoutManager(CircleActivity.this));
        news_home_recyclerview.addItemDecoration(new DividerItemDecoration(CircleActivity.this, DividerItemDecoration.HORIZONTAL));
        news_home_recyclerview.setAdapter(adapter);
        btAdd.setOnClickListener(v -> startActivity(new Intent(CircleActivity.this, ReleaseActivity.class)));

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_circle;
    }


}
