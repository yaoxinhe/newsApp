package com.example.bawei.homemodule.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.bawei.baselibrary.net.protocol.resp.BaseRespEntity;
import com.example.bawei.homemodule.bean.MySaveMessageBean;
import com.example.bawei.homemodule.bean.MySaveRe;
import com.example.bawei.homemodule.bean.NewsBean;
import com.example.bawei.homemodule.bean.NewsDetailsBean;
import com.example.bawei.homemodule.bean.NewsMessageBean;
import com.example.bawei.homemodule.bean.TypeBean;
import com.example.bawei.homemodule.repository.HomeRepository;

import java.util.List;

/**
 * @Author yaoxinhe
 * @CreateDate 2020/1/17 15:26
 * @Email 1151403054@qq.com
 */
public class HomeViewModel extends AndroidViewModel {
    HomeRepository homeRepository;
    public HomeViewModel(@NonNull Application application) {
        super(application);
        homeRepository=new HomeRepository();
    }
    public LiveData<BaseRespEntity<List<TypeBean>>> gettype(){
        return homeRepository.gettype();
    }
    public LiveData<BaseRespEntity<List<NewsBean>>> getNews(int newstype, int pagenum, int pagesize){
        return homeRepository.getNews(newstype,pagenum,pagesize);
    }
    public LiveData<BaseRespEntity<NewsDetailsBean>> getnewsDetails(String newsCode){
        return homeRepository.getnewsDetails(newsCode);
    }
    public LiveData<BaseRespEntity<List<NewsMessageBean>>> getnewsmessage(String newsCode){
        return homeRepository.getnewsmessage(newsCode);
    }
    public LiveData<BaseRespEntity<MySaveRe>> savaMessage(MySaveMessageBean mySaveMessageBean){
        return homeRepository.savaMessage(mySaveMessageBean);
    }
}
