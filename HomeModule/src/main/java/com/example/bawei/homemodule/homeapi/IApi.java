package com.example.bawei.homemodule.homeapi;

import androidx.lifecycle.LiveData;

import com.example.bawei.baselibrary.net.protocol.resp.BaseRespEntity;
import com.example.bawei.homemodule.bean.MySaveMessageBean;
import com.example.bawei.homemodule.bean.MySaveRe;
import com.example.bawei.homemodule.bean.NewsBean;
import com.example.bawei.homemodule.bean.NewsDetailsBean;
import com.example.bawei.homemodule.bean.NewsMessageBean;
import com.example.bawei.homemodule.bean.ReleaseContentRespEntity;
import com.example.bawei.homemodule.bean.TypeBean;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @Author yaoxinhe
 * @CreateDate 2020/1/17 15:38
 * @Email 1151403054@qq.com
 */
public interface IApi {
    @GET("api/NewsType/getAllTypes")
    LiveData<BaseRespEntity<List<TypeBean>>> getType();
    @GET("api/News/getNews")
    LiveData<BaseRespEntity<List<NewsBean>>> getNews(@Query("newstype")int newstype, @Query("pagenum")int pagenum, @Query("pagesize")int pagesize);
    @GET("api/NewsDetail/getNewsDetail")
    LiveData<BaseRespEntity<NewsDetailsBean>> getnewsDetails(@Query("newscode")String newscode);
    @GET("api/Comment/getComment")
    LiveData<BaseRespEntity<List<NewsMessageBean>>> getnewsmessage(@Query("newscode")String newscode);
    @POST("api/Comment/addComment")
    LiveData<BaseRespEntity<MySaveRe>> saveMessage(@Body MySaveMessageBean mySaveMessageBean);
    @GET("api/Headline/getHeadlinesForUserid")
    LiveData<BaseRespEntity<List<ReleaseContentRespEntity>>> getHeadlinesForUserid(@Query("userid") int userid);
    @POST("api/Headline/publishHeadLine")
    LiveData<BaseRespEntity<Boolean>> publishHeadLine(@Query("userid") int userid, @Query("content") String content, @Body List<String> imageList);


}
