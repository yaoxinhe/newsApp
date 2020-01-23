package com.example.bawei.usermodule.repository;

import androidx.lifecycle.LiveData;

import com.example.bawei.baselibrary.net.protocol.resp.BaseRespEntity;
import com.example.bawei.baselibrary.net.protocol.resp.UserEntity;
import com.example.bawei.usermodule.service.UserService;

public class UserRepository {

    UserService userService;
    public UserRepository(){
        userService=new UserService();
    }

    public LiveData<BaseRespEntity<UserEntity>> register(UserEntity userEntity){
        return userService.register(userEntity);
    }
    public LiveData<BaseRespEntity<UserEntity>> Login(UserEntity userEntity){
        return userService.Login(userEntity);
    }
}
