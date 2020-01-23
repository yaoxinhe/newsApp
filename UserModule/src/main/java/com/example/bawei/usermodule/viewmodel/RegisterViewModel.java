package com.example.bawei.usermodule.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.bawei.baselibrary.net.protocol.resp.BaseRespEntity;
import com.example.bawei.baselibrary.net.protocol.resp.UserEntity;
import com.example.bawei.usermodule.repository.UserRepository;

public class RegisterViewModel extends AndroidViewModel {
    UserRepository userRepository=null;
    public RegisterViewModel(@NonNull Application application) {
        super(application);
        userRepository=new UserRepository();
    }

    public LiveData<BaseRespEntity<UserEntity>> register(UserEntity userEntity){
        return userRepository.register(userEntity);
    }
    public LiveData<BaseRespEntity<UserEntity>> Login(UserEntity userEntity){
        return userRepository.Login(userEntity);
    }
}
