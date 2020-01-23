package com.example.bawei.usermodule.api;

import androidx.lifecycle.LiveData;

import com.example.bawei.baselibrary.net.protocol.resp.BaseRespEntity;
import com.example.bawei.baselibrary.net.protocol.resp.UserEntity;

import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 用户模块API
 */
public interface UserAPI {

    /**
     * 注册用户方法
     * @param userEntity
     * @return
     */
    @POST("api/User/register")
    LiveData<BaseRespEntity<UserEntity>> register(@Body UserEntity userEntity);

    /**
     * 用户登录方法
     * @return
     */
    @POST("api/User/login")
    LiveData<BaseRespEntity<UserEntity>> login(@Query("username")String username,@Query("pwd")String pwd);
}
