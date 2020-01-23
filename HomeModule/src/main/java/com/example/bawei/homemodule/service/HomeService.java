package com.example.bawei.homemodule.service;

import androidx.lifecycle.LiveData;

import com.example.bawei.baselibrary.net.RetrofitFactory;
import com.example.bawei.baselibrary.net.protocol.resp.BaseRespEntity;
import com.example.bawei.homemodule.bean.MySaveMessageBean;
import com.example.bawei.homemodule.bean.MySaveRe;
import com.example.bawei.homemodule.bean.NewsBean;
import com.example.bawei.homemodule.bean.NewsDetailsBean;
import com.example.bawei.homemodule.bean.NewsMessageBean;
import com.example.bawei.homemodule.bean.TypeBean;
import com.example.bawei.homemodule.homeapi.IApi;

import java.util.List;

/**
 * @Author yaoxinhe
 * @CreateDate 2020/1/17 15:28
 * @Email 1151403054@qq.com
 */
public class HomeService {

    /**
     * 获取type的方法
     * @return
     */
    public LiveData<BaseRespEntity<List<TypeBean>>> gettype(){
        LiveData<BaseRespEntity<List<TypeBean>>> types = RetrofitFactory.getInstance().create(IApi.class).getType();
        return types;
    }

    /**
     * 获取新闻列表的方法
     * @param newstype
     * @param pagenum
     * @param pagesize
     * @return
     */
    public LiveData<BaseRespEntity<List<NewsBean>>> getNews(int newstype, int pagenum, int pagesize){
        LiveData<BaseRespEntity<List<NewsBean>>> news = RetrofitFactory.getInstance().create(IApi.class).getNews(newstype,pagenum,pagesize);
        return news;
    }

    /**
     * 获取新闻详情的方法
     * @param newsCode
     * @return
     */

    public LiveData<BaseRespEntity<NewsDetailsBean>> getnewsDetails(String newsCode){
        LiveData<BaseRespEntity<NewsDetailsBean>> Details = RetrofitFactory.getInstance().create(IApi.class).getnewsDetails(newsCode);
        return Details;
    }
    public LiveData<BaseRespEntity<List<NewsMessageBean>>> getnewsmessage(String newsCode){
        LiveData<BaseRespEntity<List<NewsMessageBean>>> messages = RetrofitFactory.getInstance().create(IApi.class).getnewsmessage(newsCode);
        return messages;
    }
    public LiveData<BaseRespEntity<MySaveRe>> savaMessage(MySaveMessageBean mySaveMessageBean){
        return RetrofitFactory.getInstance().create(IApi.class).saveMessage(mySaveMessageBean);
    }
}
