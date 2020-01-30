package com.example.bawei.homemodule.repository;

import androidx.lifecycle.LiveData;

import com.example.bawei.baselibrary.net.RetrofitFactory;
import com.example.bawei.baselibrary.net.protocol.resp.BaseRespEntity;
import com.example.bawei.homemodule.bean.MySaveMessageBean;
import com.example.bawei.homemodule.bean.MySaveRe;
import com.example.bawei.homemodule.bean.NewsBean;
import com.example.bawei.homemodule.bean.NewsDetailsBean;
import com.example.bawei.homemodule.bean.NewsMessageBean;
import com.example.bawei.homemodule.bean.ReleaseContentRespEntity;
import com.example.bawei.homemodule.bean.TypeBean;
import com.example.bawei.homemodule.homeapi.IApi;
import com.example.bawei.homemodule.service.HomeService;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.Query;

/**
 * @Author yaoxinhe
 * @CreateDate 2020/1/17 15:29
 * @Email 1151403054@qq.com
 */
public class HomeRepository {
    HomeService homeService;
    public HomeRepository(){
        homeService=new HomeService();
    }
    public LiveData<BaseRespEntity<List<TypeBean>>> gettype(){
        return homeService.gettype();
    }
    public LiveData<BaseRespEntity<List<NewsBean>>> getNews(int newstype, int pagenum, int pagesize){
        return homeService.getNews(newstype,pagenum,pagesize);
    }
    public LiveData<BaseRespEntity<NewsDetailsBean>> getnewsDetails(String newsCode){
        return homeService.getnewsDetails(newsCode);
    }
    public LiveData<BaseRespEntity<List<NewsMessageBean>>> getnewsmessage(String newsCode){
        return homeService.getnewsmessage(newsCode);
    }
    public LiveData<BaseRespEntity<MySaveRe>> savaMessage(MySaveMessageBean mySaveMessageBean){
        return homeService.savaMessage(mySaveMessageBean);
    }
    public LiveData<BaseRespEntity<List<ReleaseContentRespEntity>>> getHeadlinesForUserid(int userid){
        return homeService.getHeadlinesForUserid(userid);
    }
    public LiveData<BaseRespEntity<Boolean>> publishHeadLine(@Query("userid") int userid, @Query("content") String content, @Body List<String> imageList) {
        return homeService.publishHeadLine(userid, content, imageList);
    }
}
