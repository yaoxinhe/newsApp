package com.example.bawei.usermodule.service;

import androidx.lifecycle.LiveData;

import com.example.bawei.baselibrary.net.RetrofitFactory;
import com.example.bawei.baselibrary.net.protocol.resp.BaseRespEntity;
import com.example.bawei.baselibrary.net.protocol.resp.UserEntity;
import com.example.bawei.usermodule.api.UserAPI;

public class UserService {
    /**
     * 注册用户账号
     * @param userEntity
     * @return
     */
    public LiveData<BaseRespEntity<UserEntity>> register(UserEntity userEntity){
        LiveData<BaseRespEntity<UserEntity>> result = RetrofitFactory.getInstance().create(UserAPI.class).register(userEntity);
        return result;
    }

    /**
     * 用户登录的方法
     * @param userEntity
     * @return
     */
    public LiveData<BaseRespEntity<UserEntity>> Login(UserEntity userEntity){
        LiveData<BaseRespEntity<UserEntity>> login = RetrofitFactory.getInstance().create(UserAPI.class).login(userEntity.getUsername(),userEntity.getPwd());
        return login;
    }
}
