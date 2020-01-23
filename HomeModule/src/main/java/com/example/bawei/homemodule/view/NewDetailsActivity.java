package com.example.bawei.homemodule.view;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.bawei.baselibrary.base.BaseActivity;
import com.example.bawei.baselibrary.net.protocol.resp.BaseRespEntity;
import com.example.bawei.homemodule.R;
import com.example.bawei.homemodule.adapter.MyMessageAdapter;
import com.example.bawei.homemodule.bean.MySaveMessageBean;
import com.example.bawei.homemodule.bean.MySaveRe;
import com.example.bawei.homemodule.bean.NewsDetailsBean;
import com.example.bawei.homemodule.bean.NewsMessageBean;
import com.example.bawei.homemodule.viewmodel.HomeViewModel;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.util.ArrayList;
import java.util.List;

public class NewDetailsActivity extends BaseActivity<HomeViewModel, ViewDataBinding> {

    private TextView tv_details;
    private WebView web_view;
    ImageView imageView;
    private RecyclerView newsdtails_recycle;
    private EditText sen_message;
    private ImageView send;
    private ImageView sc;
    private List<NewsMessageBean> newsMessageBeanList = new ArrayList<>();
    private MyMessageAdapter myMessageAdapter;
    private String newscode;
    static int a = 1;
    private ImageView fx;

    @Override
    protected void initEvent() {
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = sen_message.getText().toString();
                if (s.isEmpty()) {
                    return;
                }
                SharedPreferences yxh = getSharedPreferences("yxh", 0);
                int usercode = yxh.getInt("userid", 0);
                MySaveMessageBean mySaveMessageBean = new MySaveMessageBean(0, s, newscode, "", 0, usercode);
                LiveData<BaseRespEntity<MySaveRe>> baseRespEntityLiveData = mViewModel.savaMessage(mySaveMessageBean);
                baseRespEntityLiveData.observe(NewDetailsActivity.this, new Observer<BaseRespEntity<MySaveRe>>() {
                    @Override
                    public void onChanged(BaseRespEntity<MySaveRe> mySaveReBaseRespEntity) {
                        LiveData<BaseRespEntity<List<NewsMessageBean>>> getnewsmessage = mViewModel.getnewsmessage(newscode);
                        getnewsmessage.observe(NewDetailsActivity.this, new Observer<BaseRespEntity<List<NewsMessageBean>>>() {
                            @Override
                            public void onChanged(BaseRespEntity<List<NewsMessageBean>> listBaseRespEntity) {
                                newsMessageBeanList.clear();
                                newsMessageBeanList.addAll(listBaseRespEntity.getData());
                                myMessageAdapter.notifyDataSetChanged();
                            }
                        });
                    }
                });
            }
        });

    }

    @Override
    protected void initData() {

        LiveData<BaseRespEntity<NewsDetailsBean>> baseRespEntityLiveData = mViewModel.getnewsDetails(newscode);
        baseRespEntityLiveData.observe(this, new Observer<BaseRespEntity<NewsDetailsBean>>() {
            @Override
            public void onChanged(BaseRespEntity<NewsDetailsBean> newsDetailsBeanBaseRespEntity) {
                imageView.setVisibility(View.GONE);
                String url = newsDetailsBeanBaseRespEntity.getData().getUrl();
                String title = newsDetailsBeanBaseRespEntity.getData().getTitle();
                tv_details.setText(title);
                web_view.loadUrl(url);
                LiveData<BaseRespEntity<List<NewsMessageBean>>> getnewsmessage = mViewModel.getnewsmessage(newsDetailsBeanBaseRespEntity.getData().getNewscode());
                getnewsmessage.observe(NewDetailsActivity.this, new Observer<BaseRespEntity<List<NewsMessageBean>>>() {
                    @Override
                    public void onChanged(BaseRespEntity<List<NewsMessageBean>> listBaseRespEntity) {
                        if (listBaseRespEntity.getData() == null) {
                            return;
                        }
                        newsMessageBeanList.clear();
                        newsMessageBeanList.addAll(listBaseRespEntity.getData());
                        myMessageAdapter.notifyDataSetChanged();
                    }
                });
            }
        });

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(this, mPermissionList, 123);
        }
        tv_details = findViewById(R.id.tv_details);
        web_view = findViewById(R.id.web_view);
        imageView = findViewById(R.id.norequest);
        newsdtails_recycle = findViewById(R.id.newsdtails_recycle);
        send = findViewById(R.id.send);
        sc = findViewById(R.id.sc);
        sen_message = findViewById(R.id.sen_message);
        Glide.with(this).asGif().load("http://img.jk51.com/img_jk51/79206949.jpeg").into(imageView);
        myMessageAdapter = new MyMessageAdapter(R.layout.messageitem, newsMessageBeanList);
        newsdtails_recycle.setLayoutManager(new LinearLayoutManager(NewDetailsActivity.this));
        newsdtails_recycle.setAdapter(myMessageAdapter);
        Intent intent = getIntent();
        fx = findViewById(R.id.fx);
        final UMImage umImage = new UMImage(this, R.drawable.ic_2);
        fx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ShareAction(NewDetailsActivity.this).withMedia(umImage).setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN)
                        .setCallback(new UMShareListener() {
                            /**
                             * @descrption 分享开始的回调
                             * @param platform 平台类型
                             */
                            @Override
                            public void onStart(SHARE_MEDIA platform) {

                            }

                            /**
                             * @descrption 分享成功的回调
                             * @param platform 平台类型
                             */
                            @Override
                            public void onResult(SHARE_MEDIA platform) {
                                Toast.makeText(NewDetailsActivity.this, "成功                                        了", Toast.LENGTH_LONG).show();
                            }

                            /**
                             * @descrption 分享失败的回调
                             * @param platform 平台类型
                             * @param t 错误原因
                             */
                            @Override
                            public void onError(SHARE_MEDIA platform, Throwable t) {
                                Toast.makeText(NewDetailsActivity.this, "失                                            败" + t.getMessage(), Toast.LENGTH_LONG).show();
                            }

                            /**
                             * @descrption 分享取消的回调
                             * @param platform 平台类型
                             */
                            @Override
                            public void onCancel(SHARE_MEDIA platform) {
                                Toast.makeText(NewDetailsActivity.this, "取消                                          了", Toast.LENGTH_LONG).show();

                            }
                        }).open();
            }
        });
        newscode = intent.getStringExtra("newscode");
        myMessageAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.dianzan) {
                    a++;
                    ImageView viewById = view.findViewById(R.id.dianzan);

                    if (a % 2 == 0) {
                        viewById.setImageResource(R.drawable.ic_click);
                    }
                }
            }
        });
        sc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sc.setImageResource(R.drawable.ic_3);
            }
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_new_details;
    }


}
