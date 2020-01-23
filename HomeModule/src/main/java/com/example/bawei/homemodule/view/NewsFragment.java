package com.example.bawei.homemodule.view;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.bawei.baselibrary.base.BaseFragment;
import com.example.bawei.baselibrary.common.StartIntent;
import com.example.bawei.baselibrary.net.protocol.resp.BaseRespEntity;
import com.example.bawei.homemodule.R;
import com.example.bawei.homemodule.adapter.NewsAdapter;
import com.example.bawei.homemodule.bean.NewsBean;
import com.example.bawei.homemodule.viewmodel.HomeViewModel;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends BaseFragment<HomeViewModel, ViewDataBinding> {
    Context context;
    int newstype;
    int pagenum = 1;
    int pagesize = 20;
    private PullLoadMoreRecyclerView recycle;
    private NewsAdapter newsAdapter;
    private TextView tv_hide;
    int i = 1;
    List<NewsBean> data = new ArrayList<>();
    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            i = 1;
            tv_hide.setVisibility(View.GONE);
        }
    };


    public NewsFragment(Context context, int newstype) {
        this.context = context;
        this.newstype = newstype;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initView(Bundle savedInstanceStatev) {
        data.clear();
        data.add(new NewsBean(22, "b9b8b350a11b11e9a5cefc7774fd634d", 2, 1, "", "范冰冰独自现身商场买口红略显落寞，李晨宣传综艺打篮球有点忙", "照片中，范冰冰一身休闲打扮，正独自聚精会神地在柜台前试口红。与李晨忙着参加综艺节目相比，范冰冰尚未从逃税风波的阴影中走出，显得有几分落寞", "影视风向标", "/group/6711069249680441859/", "http://p3.pstatp.com/list/pgc-image/b5750d3d916e44699a5a59170c9f7710", "1"));
        recycle = mRoot.findViewById(R.id.recycle);
        tv_hide = mRoot.findViewById(R.id.tv_hide);
        newsAdapter = new NewsAdapter(R.layout.item, data);

    }

    @Override
    protected void initData() {
        LiveData<BaseRespEntity<List<NewsBean>>> news = mViewModel.getNews(newstype, pagenum, pagesize);
        news.observe(this, new Observer<BaseRespEntity<List<NewsBean>>>() {
            @Override
            public void onChanged(BaseRespEntity<List<NewsBean>> listBaseRespEntity) {
                List<NewsBean> datas = listBaseRespEntity.getData();
                data.addAll(datas);
                recycle.setLinearLayout();
                recycle.setAdapter(newsAdapter);
            }
        });
        recycle.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                tv_hide.setText("刷新成功");
                tv_hide.setVisibility(View.VISIBLE);
                LiveData<BaseRespEntity<List<NewsBean>>> news = mViewModel.getNews(newstype, 1, pagesize);
                news.observe(NewsFragment.this, new Observer<BaseRespEntity<List<NewsBean>>>() {
                    @Override
                    public void onChanged(BaseRespEntity<List<NewsBean>> listBaseRespEntity) {
                        data.clear();
                        data.add(new NewsBean(22, "b9b8b350a11b11e9a5cefc7774fd634d", 2, 1, "", "范冰冰独自现身商场买口红略显落寞，李晨宣传综艺打篮球有点忙", "照片中，范冰冰一身休闲打扮，正独自聚精会神地在柜台前试口红。与李晨忙着参加综艺节目相比，范冰冰尚未从逃税风波的阴影中走出，显得有几分落寞", "影视风向标", "/group/6711069249680441859/", "http://p3.pstatp.com/list/pgc-image/b5750d3d916e44699a5a59170c9f7710", "1"));
                        List<NewsBean> datas = listBaseRespEntity.getData();
                        data.addAll(datas);

                    }
                });
                recycle.setPullLoadMoreCompleted();
                final Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        i++;
                        if (i == 3) {
                            handler.sendEmptyMessage(0);
                            timer.cancel();
                        }
                    }
                }, 1000, 1000);
            }

            @Override
            public void onLoadMore() {
                tv_hide.setText("加载了" + pagesize + "条数据");
                tv_hide.setVisibility(View.VISIBLE);
                LiveData<BaseRespEntity<List<NewsBean>>> news = mViewModel.getNews(newstype, pagenum++, pagesize);
                news.observe(NewsFragment.this, new Observer<BaseRespEntity<List<NewsBean>>>() {
                    @Override
                    public void onChanged(BaseRespEntity<List<NewsBean>> listBaseRespEntity) {
                        List<NewsBean> datas = listBaseRespEntity.getData();
                        data.addAll(datas);

                    }
                });
                recycle.setPullLoadMoreCompleted();

                final Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        i++;
                        if (i == 3) {
                            handler.sendEmptyMessage(0);
                            timer.cancel();
                        }
                    }
                }, 1000, 1000);
            }
        });

    }

    @Override
    protected void doEvent() {
        newsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getContext(), NewDetailsActivity.class);
                intent.putExtra("newscode",data.get(position).getNewscode());
                startActivity(intent);
            }
        });

    }

}
